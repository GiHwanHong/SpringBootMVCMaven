package common.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sun.management.OperatingSystemMXBean;

import common.handler.CustomExcelHandler;
import common.mapper.PersonServiceMapper;
import common.message.RESTResponse;
import common.person.DeviceStatus;
import common.person.PersonDto;
import common.person.Pooltask;
import common.person.StorageStatus;
import common.repository.MailRepository;

@RequestMapping(value = "/person")
@RestController
public class PersonController implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	ThreadPoolTaskExecutor threadPoolTaskExecutor;
	static int mb = 1024*1024;
	
	@Autowired
	private MailRepository mailRepository;
	
	@Autowired
	PersonServiceMapper personMapper;
	
	String sCurDay = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(new Date());
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}
	
	
	@RequestMapping(value="/savefile",method = RequestMethod.GET)
	public String imageToExcel() throws Exception{

		// TO EXCEL FILE TEST //
		String arr_csv[] = {"flight_2017.csv"};
		
		int colsSetting=3;
		String type ="bar"; // Scatter ,TreeMap, wafer
		File[] ImageFiles = new File("E:\\workspace\\files\\imagefiles\\"+type).listFiles();
		
		String savePath = "E:\\workspace\\files\\savefiles\\"+sCurDay+" ImageSeparate_"+colsSetting+".xlsx";
		
		try {
			File file = CustomExcelHandler.makeExcelImages(ImageFiles, savePath, colsSetting, type);
			CustomExcelHandler.ReadCsvToXlsx(arr_csv);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		} 
		return "Excel Export SUCCESS";
	}
	
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public ModelAndView getPersonList() throws Exception {
		
		List<PersonDto> person = personMapper.getPersonList();
		
		// new ModelAndView("person","mailL",mailL);
	    return new ModelAndView("person","perL",person);
	}
		
	@RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<PersonDto> detailPerson(@PathVariable int id) throws Exception{
		return personMapper.detailPerson(id);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public int deletePerson(@PathVariable int id) throws Exception {
		// TODO Auto-generated method stub
		return personMapper.deletePerson(id);
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public RESTResponse insertPerson(@RequestBody PersonDto person) throws Exception {
		// TODO Auto-generated method stub
		RESTResponse restR = new RESTResponse();
		
		restR.setSuccess(true);
		restR.setMessage("정상적으로 처리되었습니다.");
		
		personMapper.insertPerson(person);
		
		return restR;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public RESTResponse updatePerson(@RequestBody PersonDto person) throws Exception {
		// TODO Auto-generated method stub
		RESTResponse restR = new RESTResponse();
		personMapper.updatePerson(person);
		restR.setSuccess(true);
		restR.setMessage("정상적으로 처리되었습니다.");
		
		return restR;
	}
	
	//@Scheduled(cron = "*/3 * * * * *")
	//@Bean(name="PersonExecutor")
    public void onCheckThreadPool() throws Exception  {
    	// threadPoolTask test.
		threadPoolTaskExecutor= new ThreadPoolTaskExecutor();
		Pooltask poolTask = null;
		
		try {
			poolTask = new Pooltask();
			
			int poolSize = threadPoolTaskExecutor.getPoolSize();//스레드 풀 사이즈 얻기
		    int activeCount = threadPoolTaskExecutor.getActiveCount();//스레드 풀 사이즈 얻기
		    int corePoolSize = threadPoolTaskExecutor.getCorePoolSize();//스레드 풀 사이즈 얻기
		    int keepAliveSeconds = threadPoolTaskExecutor.getKeepAliveSeconds();
		    int maxPoolSize = threadPoolTaskExecutor.getMaxPoolSize();
	      
		    poolTask.setPoolsize(poolSize);
		    poolTask.setActivecount(activeCount);
		    poolTask.setCorepoolsize(corePoolSize);
		    poolTask.setKeepaliveseconds(keepAliveSeconds);
		    poolTask.setMaxpoolsize(maxPoolSize);
		    
		    personMapper.onCheckThread(poolTask);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    // @SuppressWarnings("restriction")
	// @Scheduled(cron = "*/2 * * * * *")
	public void TestMonitoring() {
		// pc monitoring test 
		
    	DeviceStatus dStatus = new DeviceStatus();
    	
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		
	    double load = osBean.getSystemCpuLoad();
	    
	    File f = new File("/");
	    
	    int processors = Runtime.getRuntime().availableProcessors();
	    
	    System.out.println("Cpu cores: " + processors);
	    
	    dStatus.setCpucore(processors);
	    dStatus.setCpuusage(load);
	
	    dStatus.setTotalmemory(osBean.getTotalPhysicalMemorySize() / (1024*1024) / 1000.0);
	    dStatus.setFreememory(osBean.getFreePhysicalMemorySize() / (1024*1024) / 1000.0);

	    //Storage Info
	    List<StorageStatus> storageInfo = new ArrayList<StorageStatus>();
	    File[] roots = File.listRoots();
	    
	    Iterable<FileStore> filesystems = FileSystems.getDefault().getFileStores();
	    
	    for (FileStore fileStore : filesystems) {
			String type = fileStore.type();
			
			try {
				if ("rootfs".equals(type) == false &&
					"tmpfs".equals(type) == false &&
					"sysfs".equals(type)== false &&
					"proc".equals(type)== false &&
					"securityfs".equals(type)== false &&
					"devpts".equals(type)== false &&
					"cgroup".equals(type)== false &&
					"configfs".equals(type)== false&& fileStore.getTotalSpace() > 0) {
					
					StorageStatus storge = new StorageStatus(fileStore.getUnallocatedSpace()/mb, fileStore.getTotalSpace() /mb);
					storageInfo.add(storge);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    dStatus.setStoragestatus(storageInfo);
	    System.out.println("///// PC Monitoring /////");
	    System.out.println(dStatus.toString());
	}
}

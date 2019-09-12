package common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {
	
	ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	// @Scheduled(cron = "*/3 * * * * *")
	//@Bean(name="PersonExecutor")
    /*
	public void onCheckThread()  {
		threadPoolTaskExecutor= new ThreadPoolTaskExecutor();
      int poolSize = threadPoolTaskExecutor.getPoolSize();//스레드 풀 사이즈 얻기
      int activeCount = threadPoolTaskExecutor.getActiveCount();//스레드 풀 사이즈 얻기
      int corePoolSize = threadPoolTaskExecutor.getCorePoolSize();//스레드 풀 사이즈 얻기
      int keepAliveSeconds = threadPoolTaskExecutor.getKeepAliveSeconds();
      int maxPoolSize = threadPoolTaskExecutor.getMaxPoolSize();
      
      System.out.println("==> poolSize = {"+poolSize+ "}, activeCount = {"+activeCount+"}, corePoolSize = {"+corePoolSize +"}, keepAliveSeconds = {" + keepAliveSeconds +"}, maxPoolSize = {" +maxPoolSize+"}");
    }*/
}

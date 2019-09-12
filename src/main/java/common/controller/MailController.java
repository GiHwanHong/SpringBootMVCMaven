package common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import common.entity.MailInfoVO;
import common.message.RESTResponse;
import common.repository.MailRepository;

@RequestMapping(value = "/mailInfo")
@RestController
@Transactional
public class MailController {

	@Autowired
	private MailRepository mailRepository;	
		
	@RequestMapping(value="/mailList")
	public ModelAndView getMailList(Model model) throws Exception{
		return new ModelAndView("MailInfo");
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.POST)
	public RESTResponse delete(@PathVariable("id") int id) {
		RESTResponse restR = new RESTResponse();
		
		if(restR.isSuccess()) {
			restR.setSuccess(true);
			restR.setMessage("정상적으로 삭제되었습니다.");
			mailRepository.delete(id);
		}else {
			restR.setMessage("삭제가 되지않았습니다. 다시확인바랍니다.");
		}
		return restR;
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public RESTResponse insertMail(@RequestBody MailInfoVO mailinfo) throws Exception {
		// TODO Auto-generated method stub
		RESTResponse restR = new RESTResponse();
		
		mailRepository.save(mailinfo);
		restR.setSuccess(true);
		restR.setMessage("정상적으로 처리되었습니다.");
		
		return restR;
	}
	
	@RequestMapping(value="/update/{no}", method = RequestMethod.GET)
	public ModelAndView updateMailGet(@PathVariable("no") int idx) throws Exception {
		// TODO Auto-generated method stub
		//mailRepository.save(mailInfo);
	    return new ModelAndView("MailInfo_modify","mailInfo", mailRepository.findByIdx(idx));
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public RESTResponse updateMail(@RequestBody MailInfoVO mailinfo) throws Exception {
		// TODO Auto-generated method stub
		RESTResponse restR = new RESTResponse();
		
		mailRepository.save(mailinfo);

		restR.setSuccess(true);
		restR.setMessage("정상적으로 처리되었습니다.");
		
		return restR;
	}
}

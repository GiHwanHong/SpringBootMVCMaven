package common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.entity.MailInfoVO;
import common.repository.MailRepository;

@RequestMapping(value = "/ajaxMessage")
@RestController
public class AjaxController {
	
	@Autowired
	private MailRepository mailRepository;
	
	@RequestMapping(value="/mailListAjaxCallTest")
	public List<MailInfoVO> getMailList() throws Exception{
		return mailRepository.findAll();
	}
}

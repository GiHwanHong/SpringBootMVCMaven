package common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.entity.MailInfoVO;
import common.repository.MailRepository;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(value = "/ajaxMessage")
@RestController
@Slf4j
public class AjaxController {
	
	@Autowired
	private MailRepository mailRepository;
	
	@RequestMapping(value="/mailListData")
	public List<MailInfoVO> getMailList() throws Exception{
		log.info("[mailListData]");
		return mailRepository.findAll();
	}
}

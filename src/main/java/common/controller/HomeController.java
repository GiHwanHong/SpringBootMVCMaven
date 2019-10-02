package common.controller;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

//@RequestMapping(value = "/")
@Controller
@Slf4j
public class HomeController {
	
	@RequestMapping("/")
	public ModelAndView index() throws Exception {
		
		FileInputStream log4jRead = new FileInputStream ("/config/log4j.properties");
		Properties log4jProperty=new Properties (); 
		log4jProperty.load (log4jRead); 
		//property 타입으로 읽어서 configure와 연동
		PropertyConfigurator.configure(log4jProperty);
		log.info("[INFO] Message");
		log.trace("[TRACE] Message");
		log.warn("[WARN] Message");
		log.error("[ERROR] Message");

		return new ModelAndView("main/index");
	}
}

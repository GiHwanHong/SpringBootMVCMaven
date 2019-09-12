package common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@RequestMapping(value = "/")
@Controller

public class HomeController {

	@RequestMapping("/")
	public ModelAndView index() throws Exception {
		return new ModelAndView("main/index");
	}
}

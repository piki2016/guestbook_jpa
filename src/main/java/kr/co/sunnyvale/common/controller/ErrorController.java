package kr.co.sunnyvale.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
	@RequestMapping(value="/404", method={RequestMethod.GET})
	public String error404(Model model) throws Exception {
		return "error/404";
		
	}

	@RequestMapping(value="/403", method={RequestMethod.GET})
	public String error403(Model model) throws Exception {
		return "error/403";
		
	}
}

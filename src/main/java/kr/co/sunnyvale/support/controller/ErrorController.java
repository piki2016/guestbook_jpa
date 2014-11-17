package kr.co.sunnyvale.support.controller;

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

	@RequestMapping(value="/500", method={RequestMethod.GET})
	public String error500(Model model) throws Exception {
		return "error/500";
		
	}
}

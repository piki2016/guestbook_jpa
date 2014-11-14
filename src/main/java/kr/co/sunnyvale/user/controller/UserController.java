package kr.co.sunnyvale.user.controller;

import javax.validation.Valid;

import kr.co.sunnyvale.user.dto.UserDTO;
import kr.co.sunnyvale.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// spring-security-context에서 선언된 암호화 빈을 주입받는다.
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/login", method={RequestMethod.GET})
	public String login(Model model) throws Exception {

		return "user/login";
		
	}

	@RequestMapping(value="/logout", method={RequestMethod.GET})
	public String logout(Model model) throws Exception {

		return "user/logout";
		
	}
	
	@RequestMapping(value="/loginfail", method={RequestMethod.GET})
	public String list(Model model) throws Exception {

		return "user/loginfail";
		
	}

	@RequestMapping(value="/join", method={RequestMethod.GET})
	public String join(@ModelAttribute("user") UserDTO user, Model model) throws Exception {
		
		return "user/join";
		
	}

	@RequestMapping(value="/join", method={RequestMethod.POST})
	public String join(@ModelAttribute("user") @Valid UserDTO user, BindingResult bindingResult, Model model) throws Exception {
		if(bindingResult.hasErrors()){
			return "user/join";
		}
		
		// 암호를 암호화 하여 저장한다.
		user.setPasswd(passwordEncoder.encode(user.getPasswd()));
		System.out.println("user : " + user);
		userService.addUser(user);
		return "redirect:/guestbook/list";
		
	}
}
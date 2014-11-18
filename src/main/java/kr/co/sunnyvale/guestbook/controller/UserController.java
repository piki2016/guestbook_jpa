package kr.co.sunnyvale.guestbook.controller;

import javax.validation.Valid;

import kr.co.sunnyvale.guestbook.domain.User;
import kr.co.sunnyvale.guestbook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// spring-security-context에서 선언된 암호화 빈을 주입받는다.
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login(@RequestParam(required = false) String authfailed,
			Model model) throws Exception {
		String message = "";
		if (authfailed != null) {
			message = "id나 암호가 잘못되었습니다.";
		} 
		model.addAttribute("message", message);
		return "user/login";

	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET })
	public String logout(Model model) throws Exception {

		return "user/logout";

	}

	@RequestMapping(value = "/loginfail", method = { RequestMethod.GET })
	public String list(Model model) throws Exception {

		return "user/loginfail";

	}

	@RequestMapping(value = "/join", method = { RequestMethod.GET })
	public String join(@ModelAttribute("user") User user, Model model)
			throws Exception {

		return "user/join";

	}

	@RequestMapping(value = "/join", method = { RequestMethod.POST })
	@Transactional
	public String join(@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult, Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			return "user/join";
		}

		// 암호를 암호화 하여 저장한다.
		user.setPasswd(passwordEncoder.encode(user.getPasswd()));
		System.out.println("user : " + user);
		userRepository.save(user);
		return "redirect:/guestbook/list";

	}
}
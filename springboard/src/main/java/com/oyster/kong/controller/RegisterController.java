package com.oyster.kong.controller;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oyster.kong.domain.User;

@Controller
public class RegisterController {
	
//	@GetMapping("/register/add")
//	public String register() {
//		return "registerForm";
//	}
	
	@PostMapping("/register/save")
	public String save(User user, Model m) throws Exception {
		
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
			
			m.addAttribute("msg", msg);
			return "redirect:/register/add";
		}
		
		
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}

}

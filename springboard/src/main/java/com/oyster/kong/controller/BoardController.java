package com.oyster.kong.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("/list")
	public String list(HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL="+request.getRequestURL();
		}
		
		return "boardList";
	}

	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		if(session.getAttribute("id")!=null) {
//			return true;
//		} else {
//			return false;
//		}
		System.out.println("session이 있는지 없는지 확인용 = "+session.getId());
		return session.getAttribute("id")!=null;
	}

}

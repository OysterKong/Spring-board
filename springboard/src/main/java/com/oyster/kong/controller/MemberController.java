package com.oyster.kong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.oyster.kong.domain.MemberDto;
import com.oyster.kong.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/memberAdd")
	public String registerMember(MemberDto dto, Model m) throws Exception {
		memberService.memberAdd(dto);
		m.addAttribute("success", "회원가입 성공!");
		return "index";
	}

}

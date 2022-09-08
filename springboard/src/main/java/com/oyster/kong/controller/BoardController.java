package com.oyster.kong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oyster.kong.domain.BoardDto;
import com.oyster.kong.domain.PageHandler;
import com.oyster.kong.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL="+request.getRequestURL();
		}
		
		if(page==null) page=1;
		if(pageSize==null) pageSize=10;
		
		try {
			
			int totalCnt = boardService.getCount();
			PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
			
			// page 와 pageSize를 받아서 map에 담은뒤 계산
			Map map = new HashMap();
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);
			
			// boardService의 getPage에 map을 넘겨주고 이를 list에 담은뒤 model에 저장
			List<BoardDto> list = boardService.getPage(map);
			System.out.println("list에 무엇이 담겼는지 확인용 = "+list);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler); // pageHandler에 총 페이지수, 현재페이지, 한페이지당 사이즈 = 10개씩을 담아 jsp로 넘김
		} catch (Exception e) {
			e.printStackTrace();
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

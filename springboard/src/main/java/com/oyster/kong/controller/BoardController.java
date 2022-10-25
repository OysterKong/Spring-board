package com.oyster.kong.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oyster.kong.domain.BoardDto;
import com.oyster.kong.domain.PageHandler;
import com.oyster.kong.domain.SearchCondition;
import com.oyster.kong.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@PostMapping("/modify")
	public String modify(BoardDto boardDto,Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr ) {
		
		String writer = (String) session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {
			int rowCnt = boardService.modify(boardDto);
			
			if(rowCnt!=1) {
				throw new Exception("Modify failed");
			}
			rattr.addFlashAttribute("msg", "Modify well completed");
			rattr.addAttribute("page", page);
			rattr.addAttribute("pageSize", pageSize);
			
			return "redirect:/board/list";
			
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("msg", "Modify Error");
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
			return "board";
		}
		
	}
	
	
	@PostMapping("/write")
	public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr ) {
		
		String writer = (String) session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {
			int rowCnt = boardService.write(boardDto);
			
			if(rowCnt!=1) {
				throw new Exception("Write failed");
			}
			rattr.addFlashAttribute("msg", "Write well completed");
			
			return "redirect:/board/list";  // 글쓰기가 성공하면 체일 첫번째 페이지로 이동하도록 redirect/board/list
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("msg", "Write Error");
			return "board";   // 예외 발생시 내가 썼던 내용으로 돌아갈 수 있게 board.jsp 로 설정
		}
	}
	
	
	
	@GetMapping("/write")
	public String write(Model m) {
		m.addAttribute("mode", "new");
		return "board"; // 읽기와 쓰기에 사용,  쓰기에 사용할때는 mode를 new로 지정해서 readonly를 해제하는 방식
	}
	
	
	@PostMapping("/remove")
	public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
		
		String writer = (String)session.getAttribute("id");
		try {
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
			
			int rowCnt = boardService.remove(bno, writer);
			
			if(rowCnt!=1) {
				throw new Exception("board remove error");
			}
			rattr.addFlashAttribute("msg", "Delete well completed"); // rattr.addFlashAttribute = 새로고침시 계속 alert뜨는 것 방지 , session에 저장했다가 사용하고 삭제
			
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "Delete error");
		}
	
		return "redirect:/board/list";	
	}
	
	
	
	@GetMapping("/read")
	public String read(Integer bno, Integer page, Integer pageSize, Model m, RedirectAttributes rattr) {
		
		try {
			BoardDto boardDto = boardService.read(bno); //boardList에서 전달받는 bno를 받아서 Db에서 읽어와서 Dto에 저장
			m.addAttribute("boardDto", boardDto);  // Dto에 저장된 title과 content를 board.jsp로 model에 담아 전달
			m.addAttribute("page", page); // 목록버튼을 눌렀을시 기존의 리스트로 돌아가기위해 page 정보를 view로 전달해줘야함
			m.addAttribute("pageSize", pageSize); // 목록버튼을 눌렀을시 기존의 리스트로 돌아가기위해 pageSize 정보를 view로 전달해줘야함
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addAttribute("page", page);
			rattr.addAttribute("pageSize", pageSize);
			rattr.addFlashAttribute("msg", "Read error");
			return "redirect:/board/list";
		}
		
		return "board";
	}
	
	
	
	@GetMapping("/list")
	public String list(SearchCondition sc, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL="+request.getRequestURL(); //로그인을 안했으면 로그인 화면으로 이동
		}

		try {
			int totalCnt = boardService.getSearchResultCnt(sc);
			m.addAttribute("totalCnt", totalCnt);
			
			PageHandler pageHandler = new PageHandler(totalCnt, sc);

			List<BoardDto> list = boardService.getSearchResultPage(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler); // pageHandler에 총 페이지수, 현재페이지, 한페이지당 사이즈 = 10개씩을 담아 jsp로 넘김
			
			Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
			m.addAttribute("startOfToday", startOfToday.toEpochMilli());
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("msg", "List error");
			m.addAttribute("totalCnt", 0);
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
//		System.out.println("session이 있는지 없는지 확인용 = "+session.getId());
		return session.getAttribute("id")!=null;
	}

}

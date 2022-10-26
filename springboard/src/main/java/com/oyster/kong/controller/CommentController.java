package com.oyster.kong.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oyster.kong.domain.CommentDto;
import com.oyster.kong.service.CommentService;

@RestController   // @Controller + @ResponseBody 합친 것
public class CommentController {
	
	@Autowired
	CommentService service;
	
	
	// 댓글을 수정하는 메서드
	@PatchMapping("/comments/{cno}")
	public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto, HttpSession session) {
		String commenter = (String) session.getAttribute("id");
//		String commenter = "asdf";
		dto.setCommenter(commenter);
		dto.setCno(cno);
		System.out.println("dto 댓글 수정시 확인 = " + dto);
		try {
			if(service.modify(dto)!=1) {
				throw new Exception("Modify failed");
			}
			
			return new ResponseEntity<>("Modify well completed", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Modify error", HttpStatus.BAD_REQUEST); 
		}
	}
	
	
	// 댓글을 등록하는 메서드
	@PostMapping("/comments")
	public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session) {
		String commenter = (String) session.getAttribute("id");
//		String commenter = "asdf";
		dto.setCommenter(commenter);
		dto.setBno(bno);
		System.out.println("dto 댓글 등록시 확인 = " + dto);
		
		try {
			if(service.write(dto)!=1) {
				throw new Exception("Write failed");
			}
			
			return new ResponseEntity<>("Write well completed", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Write error", HttpStatus.BAD_REQUEST); 
		}
	}
	
	
	// 지정된 댓글(cno)를 삭제하는 메서드
	@DeleteMapping("/comments/{cno}")
	public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
		String commenter = (String) session.getAttribute("id");
//		String commenter = "asdf";	
		try {
			int rowCnt = service.remove(cno, bno, commenter);
			
			if(rowCnt!=1) {
				throw new Exception("Delete failed");
			}
			return new ResponseEntity<>("Delete well completed", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Delete error", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// 지정된 게시물(bno)의 모든 댓글을 가져오는 메서드
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDto>> list(Integer bno) {
			List<CommentDto> list = null;
		try {
			list = service.getList(bno);
			return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);  // Status code 200 = 성공시 코드도 같이 Entity에 담아 보냄
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST); // Status code 400 = 실패시 코드도 같이 Entity에 담아 보냄
		}

	}

}

package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;

@Controller
public class BoardController {
	
	// 멤버변수로 부모 인터페이스 정의 => 자동으로 자식 클래스 객체생성 
	// 스프링 객체생성 방식 => 의존관계주입(DI : Dependency Injection)
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write() {
		
		// 주소변경 없이 이동
		// /WEB-INF/views/board/writeForm.jsp
		return "board/writeForm";
	}

	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardDTO boardDTO) {
		System.out.println("BoardController writePro()");
		
		// 글쓰기 작업 메서드 호출
		boardService.insertBoard(boardDTO);
		
		// 주소변경 하면서 이동
		// /WEB-INF/views/board/writeForm.jsp
		return "redirect:/board/list";
	}
	
	// http://localhost:8080/myweb/board/list
	// http://localhost:8080/myweb/board/list?pageNum=2
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		System.out.println("BoardController list()");	
		
		// 한 화면에 보여줄 글 개수 설정
		int pageSize=10;
		
		// 현페이지 번호 가져오기
		String pageNum=request.getParameter("pageNum");
		if(pageNum == null) {
			// pageNum 없으면 1페이지 설정
			pageNum="1";
		}
		int currentPage=Integer.parseInt(pageNum);
		
		PageDTO pageDTO=new PageDTO();
		pageDTO.setPageSize(pageSize);
		pageDTO.setPageNum(pageNum);
		pageDTO.setCurrentPage(currentPage);
		
		List<BoardDTO> boardList=boardService.getBoardList(pageDTO);
		
		// 페이징
		int count=boardService.getBoardCount();
		int pageBlock=10;
		int startPage=(currentPage-1)/pageBlock*pageBlock+1;
		int endPage=startPage+pageBlock-1;
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		pageDTO.setCount(count);
		pageDTO.setPageBlock(pageBlock);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setPageCount(pageCount);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageDTO", pageDTO);
		
		// 주소변경 없이 이동
		// /WEB-INF/views/board/list.jsp
		return "board/list";
	}
	
	@RequestMapping(value = "/board/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Model model) {
		int num=Integer.parseInt(request.getParameter("num"));
		
		BoardDTO boardDTO=boardService.getBoard(num);
				
		model.addAttribute("boardDTO", boardDTO);
		
		// 주소변경 없이 이동
		// /WEB-INF/views/board/writeForm.jsp
		return "board/content";
	}
}

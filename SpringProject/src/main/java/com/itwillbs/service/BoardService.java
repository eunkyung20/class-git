package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

public interface BoardService {
	// 추상메서드
	public void insertBoard(BoardDTO boardDTO);
	
	public List<BoardDTO> getBoardList(PageDTO pageDTO);

	public int getBoardCount(); //전체 글의 개수

	public BoardDTO getBoard(int num); // num에 대한 게시판 글 가져오기

	public void updateBoard(BoardDTO boardDTO);

	public void deleteBoard(int num);
	
	
}

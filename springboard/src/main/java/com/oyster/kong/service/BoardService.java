package com.oyster.kong.service;

import java.util.List;
import java.util.Map;

import com.oyster.kong.domain.BoardDto;

public interface BoardService {

	BoardDto read(Integer bno) throws Exception;

	int getCount() throws Exception;

	int write(BoardDto boardDto) throws Exception;

	int modify(BoardDto boardDto) throws Exception;

	int remove(Integer bno, String writer) throws Exception;

	List<BoardDto> getList() throws Exception;

	List<BoardDto> getPage(Map map) throws Exception;

//	int getSearchResultCnt(SearchCondition sc) throws Exception;
//
//	List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception;

}
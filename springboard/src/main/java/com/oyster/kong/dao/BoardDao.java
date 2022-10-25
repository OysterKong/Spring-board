package com.oyster.kong.dao;

import com.oyster.kong.domain.*;

import java.util.List;
import java.util.Map;

public interface BoardDao {

	BoardDto select(Integer bno) throws Exception;

	int count() throws Exception;

	int deleteAll();

	int insert(BoardDto dto) throws Exception;

	int update(BoardDto dto) throws Exception;

	int delete(Integer bno, String writer) throws Exception;

	List<BoardDto> selectAll() throws Exception;

	List<BoardDto> selectPage(Map map) throws Exception;

	int increaseViewCnt(Integer bno) throws Exception; // int update(String statement, Object parameter)

	int searchResultCnt(SearchCondition sc) throws Exception; // T selectOne(String statement, Object parameter)

	List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception; // List<E> selectList(String statement, Object parameter)

	int updateCommentCnt(Integer bno, int cnt);

}
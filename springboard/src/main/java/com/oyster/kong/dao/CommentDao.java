package com.oyster.kong.dao;

import java.util.List;

import com.oyster.kong.domain.CommentDto;

public interface CommentDao {

	int deleteAll(Integer bno) throws Exception;

	int count(Integer bno) throws Exception;

	int delete(Integer cno, String commenter) throws Exception;

	int insert(CommentDto dto) throws Exception;

	List<CommentDto> selectAll(Integer bno) throws Exception;

	CommentDto select(Integer cno) throws Exception;

	int update(CommentDto dto) throws Exception;

}
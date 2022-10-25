package com.oyster.kong.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.oyster.kong.domain.CommentDto;

public interface CommentService {

	int getCount(Integer bno) throws Exception;

	int remove(Integer cno, Integer bno, String commenter) throws Exception;

	int write(CommentDto commentDto) throws Exception;

	List<CommentDto> getList(Integer bno) throws Exception;

	CommentDto read(Integer cno) throws Exception;

	int modify(CommentDto commentDto) throws Exception;

}
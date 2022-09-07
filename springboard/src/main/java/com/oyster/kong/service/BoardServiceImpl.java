package com.oyster.kong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oyster.kong.dao.BoardDao;

@Service
public class BoardServiceImpl {
	
	@Autowired
	private BoardDao boardDao;

}

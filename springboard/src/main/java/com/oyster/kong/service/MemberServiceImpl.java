package com.oyster.kong.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oyster.kong.dao.MemberDao;
import com.oyster.kong.domain.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	@Override
	public void memberAdd(MemberDto dto) throws Exception {
		memberDao.memberAdd(dto);
	}
	
	@Override
	public MemberDto login(Map<String, String> map) throws Exception {
		return memberDao.login(map);
	}

}

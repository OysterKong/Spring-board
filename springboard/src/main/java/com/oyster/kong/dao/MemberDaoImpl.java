package com.oyster.kong.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oyster.kong.domain.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSession session;
	private static String namespace="com.oyster.kong.dao.MemberMapper.";
	
	@Override
	public void memberAdd(MemberDto dto) throws Exception {
		session.insert(namespace + "memberAdd", dto);
	}
	
	@Override
	public MemberDto login(Map<String, String> map) throws Exception {
		return session.selectOne(namespace + "login", map);
	}

}

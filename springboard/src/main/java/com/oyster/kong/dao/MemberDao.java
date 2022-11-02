package com.oyster.kong.dao;

import java.util.Map;

import com.oyster.kong.domain.MemberDto;

public interface MemberDao {

	void memberAdd(MemberDto dto) throws Exception;

	MemberDto login(Map<String, String> map) throws Exception;

}
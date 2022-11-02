package com.oyster.kong.service;

import java.util.Map;

import com.oyster.kong.domain.MemberDto;

public interface MemberService {

	void memberAdd(MemberDto dto) throws Exception;

	MemberDto login(Map<String, String> map) throws Exception;

}
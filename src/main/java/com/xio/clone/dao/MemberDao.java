package com.xio.clone.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xio.clone.dto.Member;

@Mapper
public interface MemberDao {

	Member getMemberByLoginId(String loginId);

	int getLastInsertId();

	void join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email);
}

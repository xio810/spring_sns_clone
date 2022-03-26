package com.xio.clone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xio.clone.dao.ArticleDao;
import com.xio.clone.dao.MemberDao;
import com.xio.clone.dto.Member;
import com.xio.clone.dto.ResultData;

@Service
public class MemberService {
	@Autowired
	private ArticleDao articleDao;
	private MemberDao memberDao;
	
	
	public Member getMemberByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return null;
	}


	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

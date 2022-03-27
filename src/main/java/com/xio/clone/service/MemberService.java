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
	private MemberDao memberDao;

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		memberDao.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		int id = memberDao.getLastInsertId();

		return new ResultData("S-1", "회원가입이 완료되었습니다.", "id", id);
	}

}

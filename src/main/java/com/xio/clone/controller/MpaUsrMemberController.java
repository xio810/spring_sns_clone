package com.xio.clone.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.clone.dto.Article;
import com.xio.clone.dto.Member;
import com.xio.clone.dto.ResultData;
import com.xio.clone.service.MemberService;
import com.xio.clone.util.Util;

@Controller
public class MpaUsrMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/mpaUsr/member/join")
	public String showJoin() {
		return "mpaUsr/member/join";
	}

	@RequestMapping("/mpaUsr/member/doJoin")
	public String doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname,
			String cellphoneNo, String email) {
		Member oldMember = memberService.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return Util.msgAndBack(req, loginId + "(은)는 이미 사용중인 로그인아이디 입니다.");
		}

		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (joinRd.isFail()) {
			return Util.msgAndBack(req, joinRd.getMsg());
		}

		return Util.msgAndReplace(req, joinRd.getMsg(), "/");
	}

	@RequestMapping("/mpaUsr/member/login")
	public String showLogin(HttpServletRequest req) {
		return "mpaUsr/member/login";
	}

}
package com.xio.clone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.clone.dto.Article;
import com.xio.clone.dto.ResultData;
import com.xio.clone.util.Util;

@Controller
public class MpaUsrArticleController {

	private int articleLastId = 0;

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();

		Article article = new Article(id, regDate, updateDate, title, body);

		articleLastId = id;

		ResultData resultData = new ResultData("s-1", id + "번 게시물이 등록되었습니다.", "article", article);

		return resultData;
	}
}

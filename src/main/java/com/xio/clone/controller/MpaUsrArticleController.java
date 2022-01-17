package com.xio.clone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.clone.dto.Article;
import com.xio.clone.dto.ResultData;
import com.xio.clone.util.Util;

import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;

@Controller
public class MpaUsrArticleController {

	private List<Article> articles;
	private int articleLastId;

	public MpaUsrArticleController() {
		articles = new ArrayList<>();
		articleLastId = 0;
		makeTestData();
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		int id = writeArticle(title, body);
		Article article = getArticleById(id);

		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "article", article);
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article article = getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 글이 없습니다.", "id", id);
		}

		return new ResultData("S-1", article.getId() + "번 글 입니다.", "article", article);
	}

	private int writeArticle(String title, String body) {
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();

		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);

		articleLastId = id;

		return id;
	}

	private Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}

	private void makeTestData() {
		for (int i = 1; i < 4; i++) {
			writeArticle("title" + i, "body" + i);
		}
	}
}
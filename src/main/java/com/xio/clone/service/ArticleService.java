package com.xio.clone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xio.clone.dto.Article;
import com.xio.clone.util.Util;

@Service
public class ArticleService {

	private int lastInsertId;
	private List<Article> articles;

	public ArticleService() {
		lastInsertId = 0;
		articles = new ArrayList<>();
		makeTestData();
	}

	public boolean deleteArticle(Integer id) {
		Article article = getArticleById(id);
		if (article == null) {
			return false;
		}
		articles.remove(article);
		return true;
	}

	public boolean modifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);

		if (article == null) {
			return false;
		}

		article.setUpdateDate(Util.getNowDateStr());
		article.setTitle(title);
		article.setBody(body);

		return true;
	}

	public int writeArticle(String title, String body) {
		int id = lastInsertId + 1;

		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();

		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);

		lastInsertId = id;

		return id;
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public void makeTestData() {
		for (int i = 1; i < 4; i++) {
			writeArticle("title" + i, "body" + i);
		}
	}

}

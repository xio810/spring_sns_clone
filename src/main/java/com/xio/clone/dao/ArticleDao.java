package com.xio.clone.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.xio.clone.dto.Article;
import com.xio.clone.util.Util;

@Component
public class ArticleDao {
	private int lastInsertId;
	private List<Article> articles;

	public ArticleDao() {
		lastInsertId = 0;
		articles = new ArrayList<>();
		makeTestData();
	}

	private void makeTestData() {
		for (int i = 0; i < 4; i++) {
			writeArticle(i + "title", i + "body");
		}
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

	public boolean modifyArticle(Integer id, String title, String body) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return false;
		}
		
		article.setUpdateDate(Util.getNowDateStr());
		article.setTitle(title);
		article.setBody(body);
		
		return true;
	}

	public Article getArticleById(Integer id) {

		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public boolean deleteArticle(Integer id) {
		Article article = getArticleById(id);
		
		if (article == null ) {
			return false;
		}
		
		articles.remove(article);
		
		return true;
	}
}

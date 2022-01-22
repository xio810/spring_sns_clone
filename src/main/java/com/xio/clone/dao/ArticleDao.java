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
		for (int i =1; i < 4; i ++) {
			writeArticle(i+"title", i+"body");
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
	
	public Article getArticleById(Integer id) {
		for(Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public boolean modifyArticle(Integer id, String title, String body) {
		Article article = getArticleById(id);
		
		article.setUpdateDate(Util.getNowDateStr());
		article.setTitle(title);
		article.setBody(body);
		
		return true;
	}

	public boolean deleteArticle(Integer id) {
		
		Article article = getArticleById(id);
		
		articles.remove(article);
		
		return true;
	}

}

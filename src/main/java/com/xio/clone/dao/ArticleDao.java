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

}

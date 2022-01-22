package com.xio.clone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xio.clone.dao.ArticleDao;
import com.xio.clone.dto.Article;
import com.xio.clone.dto.ResultData;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public ResultData writeArticle(String title, String body) {

		int id = articleDao.writeArticle(title, body);

		return new ResultData("S-1", id + " 번 게시물 등록.", "id", id);
	}

}

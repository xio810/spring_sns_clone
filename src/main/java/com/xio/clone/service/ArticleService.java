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

	public ResultData getArticleById(Integer id) {

		Article article = articleDao.getArticleById(id);

		if (article == null) {
			return new ResultData("F-2", id + "번 게시물은 없습니다.");
		}

		return new ResultData("S-1", id + "번 게시물 입니다.", "article", article);
	}

	public ResultData modifyArticle(Integer id, String title, String body) {

		Article article = articleDao.getArticleById(id);

		if (article == null) {
			return new ResultData("F-2", id + "번 게시물은 없습니다.");
		}

		articleDao.modifyArticle(id, title, body);

		return new ResultData("S-1", id + "번 게시물이 수정되었습니다.", "id", id);
	}

	public ResultData deleteArticle(Integer id) {
		
		Article article = articleDao.getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-2", id + "번 게시물은 없습니다.");
		}
		
		articleDao.deleteArticle(id);
		
		return new ResultData("S-1", id + "번 게시물이 삭제되었습니다.", "id", id);
	}

}

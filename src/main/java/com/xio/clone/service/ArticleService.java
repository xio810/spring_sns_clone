package com.xio.clone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xio.clone.dao.ArticleDao;
import com.xio.clone.dto.Article;
import com.xio.clone.dto.Board;
import com.xio.clone.dto.ResultData;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public ResultData modifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", "존재하지 않는 게시물 번호입니다.", "id", id);
		}

		articleDao.modifyArticle(id, title, body);

		return new ResultData("S-1", "게시물이 수정되었습니다.", "id", id);
	}

	public ResultData deleteArticle(int id) {
		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", "존재하지 않는 게시물 번호입니다.", "id", id);
		}

		articleDao.deleteArticle(id);

		return new ResultData("S-1", id + "번 게시물이 삭제되었습니다.", "id", id, "boardId", article.getBoardId());
	}

	public ResultData writeArticle(int boardId, int memberId, String title, String body) {

		articleDao.writeArticle(boardId, memberId, title, body);

		int id = articleDao.getLastInsertId();

		return new ResultData("S-1", "게시물이 작성되었습니다.", "id", id);
	}

	public Article getArticleById(Integer id) {
		return articleDao.getArticleById(id);
	}

	private boolean isEmpty(Article article) {
		if (article == null) {
			return true;
		}
		return false;
	}

	public Board getBoardById(int id) {

		return articleDao.getBoardById(id);
	}

	public int getArticlesTotalCount(int boardId, String searchKeywordTypeCode, String searchKeyword) {

		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		return articleDao.getArticlesTotalCount(boardId, searchKeywordTypeCode, searchKeyword);
	}

	public List<Article> getForPrintArticles(int boardId, String searchKeywordTypeCode, String searchKeyword,
			int itemsCountInAPage, int page) {

		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		// page가 1이면 0부터 시작, page가 2이면 20부터 시작
		int limitFrom = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;

		return articleDao.getForPrintArticles(boardId, searchKeywordTypeCode, searchKeyword, limitFrom, limitTake);
	}
	
	public Article getForPrintArticleById (int id) {
		return articleDao.getForPrintArticleById(id);
	}
}

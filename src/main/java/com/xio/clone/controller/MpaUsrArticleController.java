package com.xio.clone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.clone.dto.Article;
import com.xio.clone.dto.ResultData;
import com.xio.clone.service.ArticleService;
import com.xio.clone.util.Util;

@Controller
public class MpaUsrArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {

		if (Util.isEmpty(title)) {
			return new ResultData("F-2", "제목을 입력해주세요.");
		}
		if (Util.isEmpty(body)) {
			return new ResultData("F-2", "내용을 입력해주세요.");
		}

		int id = articleService.writeArticle(title, body);
		Article article = articleService.getArticleById(id);

		return new ResultData("S-1", id + "번 게시물이 작성되었습니다.", "article", article);
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(Integer id) {

		if (id == null) {
			return new ResultData("F-2", "번호를 입력해주세요.");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 게시물이 없습니다.", "id", id);
		}
		return new ResultData("S-1", id + "번 게시물 입니다.", "article", article);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {
		if (id == null) {
			return new ResultData("F-2", "번호를 입력해주세요.");
		}
		if (Util.isEmpty(title)) {
			return new ResultData("F-2", "제목을 입력해주세요.");
		}
		if (Util.isEmpty(body)) {
			return new ResultData("F-2", "내용을 입력해주세요.");
		}

		boolean modified = articleService.modifyArticle(id, title, body);

		if (modified == false) {
			return new ResultData("F-1", id + "번 글은 없습니다.");
		}

		return new ResultData("S-1", id + "번 글이 수정되었습니다.", "article", articleService.getArticleById(id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {
		if (id == null) {
			return new ResultData("F-2", "번호를 입력해주세요.");
		}
		boolean deleted = articleService.deleteArticle(id);
		
		if(deleted == false) {
			return new ResultData("F-1", id + "번 글은 없습니다.");
		}
		return new ResultData("S-1", id + "번 게시물을 삭제하였습니다.", "id",id);
	}

}
package com.xio.clone.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.clone.dto.Article;
import com.xio.clone.dto.Board;
import com.xio.clone.dto.ResultData;
import com.xio.clone.service.ArticleService;
import com.xio.clone.util.Util;

@Controller
public class MpaUsrArticleController {
	@Autowired
	private ArticleService articleService;

	private String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("msg", msg);
		return "common/redirect";
	}

	@RequestMapping("/mpaUsr/article/list")
	public String showList(HttpServletRequest req, int boardId) {
		Board board = articleService.getBoardById(boardId);

		if (board == null) {
			return msgAndBack(req, boardId + "번 게시판은 없습니다.");
		}

		req.setAttribute("board", board);

		return "mpaUsr/article/list";
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {

		if (Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 입력해주세요.");
		}
		if (Util.isEmpty(body)) {
			return new ResultData("F-1", "내용을 입력해주세");
		}

		return articleService.writeArticle(title, body);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {

		if (id == null) {
			return new ResultData("F-1", "번호를 입력해주세요.");
		}
		if (Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 입력해주세요.");
		}
		if (Util.isEmpty(body)) {
			return new ResultData("F-1", "내용을 입력해주세요.");
		}

		return articleService.modifyArticle(id, title, body);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {

		if (id == null) {
			return new ResultData("F-1", "번호를 입력해주세요.");
		}

		return articleService.deleteArticle(id);
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Article getArticle(Integer id) {

		return articleService.getArticleById(id);
	}
}
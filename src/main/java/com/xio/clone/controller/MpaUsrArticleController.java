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
	
	//알림 후 뒤로가기 
	private String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("msg", msg);
		//historyBack이 true면 실행 
		req.setAttribute("historyBack", true);
		
		return "common/redirect";
	}
	//알림 후 게시판번호로 이동 
	private String msgAndReplace(HttpServletRequest req, String msg, String replaceUrl) {
		req.setAttribute("msg", msg);
		//boardId에 맞는 게시판번호로 이동 
		req.setAttribute("replaceUrl", replaceUrl);

		return "common/redirect";
	}

	@RequestMapping("/mpaUsr/article/list")
	public String showList(HttpServletRequest req, int boardId) {
		Board board = articleService.getBoardById(boardId);

		if (board == null) {
			return msgAndBack(req, boardId + "번 게시판은 없습니다.");
		}

		req.setAttribute("board", board);
		
		int totalItemsCount = articleService.getArticlesTotalCount(boardId);
		
		req.setAttribute("totalItemsCount", totalItemsCount);

		return "mpaUsr/article/list";
	}

	@RequestMapping("/usr/article/doDelete")
	public String doDelete(HttpServletRequest req, Integer id) {

		if (id == null) {
			return msgAndBack(req, "id를 입력해주세요.");
		}

		ResultData rd = articleService.deleteArticle(id);
		
		if(rd.isFail()) {
			return msgAndBack(req, rd.getMsg());
		}
		String redirectUrl = "../article//list?boardId=" + rd.getBody().get("boardId");
		
		return msgAndReplace(req, rd.getMsg(), redirectUrl);
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

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Article getArticle(Integer id) {

		return articleService.getArticleById(id);
	}
}
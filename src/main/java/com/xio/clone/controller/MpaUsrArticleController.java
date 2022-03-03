package com.xio.clone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.clone.dto.Article;
import com.xio.clone.dto.Board;
import com.xio.clone.dto.ResultData;
import com.xio.clone.service.ArticleService;
import com.xio.clone.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MpaUsrArticleController {
	@Autowired
	private ArticleService articleService;

	// 알림 후 뒤로가기
	private String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("msg", msg);
		// historyBack이 true면 실행
		req.setAttribute("historyBack", true);

		return "common/redirect";
	}

	// 알림 후 게시판번호로 이동
	private String msgAndReplace(HttpServletRequest req, String msg, String replaceUrl) {
		req.setAttribute("msg", msg);
		// boardId에 맞는 게시판번호로 이동
		req.setAttribute("replaceUrl", replaceUrl);

		return "common/redirect";
	}

	// @RequestParam(defaultValue = "1") int page => page값을 입력하지 않아도 기본으로 1이 들어가있게
	// list에서 받는 데이터들 -> servlet req, boardId, searchKeywordType, searchKeyword,
	// page => 1페이지, 2페이지 번호
	@RequestMapping("/mpaUsr/article/list")
	public String showList(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId,
			String searchKeywordType, String searchKeyword, @RequestParam(defaultValue = "1") int page) {
		Board board = articleService.getBoardById(boardId);
//
//		// 개발모드일때는 콘솔에 출력되나 실행모드일 때는 출력되지 않음. app~.yml에서 debug를 info로 바꾸면 뜨지않음.
//		// syso와 같으나 log는 더 자세하게 콘솔에 나옴
//		log.debug("searchKeyword : " + searchKeyword);

		// titleAndBody -> 제목+내용
		if (Util.isEmpty(searchKeywordType)) {
			searchKeywordType = "titleAndBody";
		}

		if (board == null) {
			return msgAndBack(req, boardId + "번 게시판은 없습니다.");
		}

		req.setAttribute("board", board);
		// 총 게시물 개수
		int totalItemsCount = articleService.getArticlesTotalCount(boardId, searchKeywordType, searchKeyword);

		if (searchKeyword == null || searchKeyword.trim().length() == 0) {

		}

		req.setAttribute("totalItemsCount", totalItemsCount);

		// 한 페이지당 보여줄 수 있는 게시물 개수
		int itemsCountInAPage = 20;
		// 총 페이지 수 //총 개시물 개수 / 20 ceil은 올림
		int totalPage = (int) Math.ceil(totalItemsCount / (double) itemsCountInAPage);

		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);

		List<Article> articles = articleService.getForPrintArticles(boardId, searchKeywordType, searchKeyword,
				itemsCountInAPage, page);

		req.setAttribute("articles", articles);

		return "mpaUsr/article/list";
	}

	@RequestMapping("/usr/article/doDelete")
	public String doDelete(HttpServletRequest req, Integer id) {

		if (id == null) {
			return msgAndBack(req, "id를 입력해주세요.");
		}

		ResultData rd = articleService.deleteArticle(id);

		if (rd.isFail()) {
			return msgAndBack(req, rd.getMsg());
		}
		String redirectUrl = "../article//list?boardId=" + rd.getBody().get("boardId");

		return msgAndReplace(req, rd.getMsg(), redirectUrl);
	}
	
	@RequestMapping("/usr/article/write")
	public String showWrite(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId) {
		Board board = articleService.getBoardById(boardId);
		
		if(board == null) {
			return msgAndBack(req, boardId+"번 게시판이 없습니다.");
		}
		
		req.setAttribute("board", board);
		
		return "mpaUsr/article/write";
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
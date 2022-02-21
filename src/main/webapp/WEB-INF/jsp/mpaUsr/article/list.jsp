<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="<span><i class='far fa-clipboard'></i></span> <span>${board.name} Article List</span>" />

<%@ include file="../common/head.jspf"%>

<div class="section section-article-list">
  <div class="container mx-auto">

    <div class="total-items">
      <!-- 총 게시물 -->
      <span>TOTAL ITEMS : </span>
      <span>${totalItemsCount}</span>
    </div>

    <div class="total-pages">
      <!-- 총 페이지 -->
      <span>TOTAL PAGES : </span>
      <span>${totalPage}</span>
    </div>

    <div class="page">
      <!-- 현재페이지 -->
      <span>CURRENT PAGE : </span>
      <span>${page}</span>
    </div>

    <hr />
    <hr />

    <div class="search-form-box mt-2">
      <form action="" class="grid gap-2">
        <input type="hidden" name="boardId" value="${board.id}" />
        <input value="${param.searchKeyword}" class="input input-bordered" name="searchKeyword" type="text"
          placeholder="검색어를 입력해주세요." maxlength="10" />
        <input type="submit" class="btn btn-sm btn-primary" value="검색" />
      </form>
    </div>

    <div class="articles mt-2">
      <c:forEach items="${articles}" var="article">
        <div>
          ID : ${article.id}
          <br>
          regDate : ${article.regDate}
          <br>
          updateDate : ${article.updateDate}
          <br>
          TITLE : ${article.title}
          <br>
        </div>
        <hr />
      </c:forEach>
    </div>

  </div>
</div>


<%@ include file="../common/foot.jspf"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="<span><i class='far fa-clipboard'></i></span> <span>${board.name}</span>" />
<div style = "background-color : pink; margin-top : 90px;"> hello world ${board.code}</div>

<%@ include file="../common/head.jspf"%>
<%@ include file="../common/foot.jspf"%>

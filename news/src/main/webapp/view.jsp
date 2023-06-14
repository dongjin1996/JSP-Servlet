<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
	<div class="news_wrap">
		<div class="news_title">
			<strong>뉴스게시판</strong>
		</div>
		<div class="news_view_wrap">
			<div class="news_view">
				<div class="title">${news.title }</div>
				<div class="info" style="position: relative;">
					<dl>
						<dt>번호 ${news.news_no }</dt>
						<dd></dd>
					</dl>
					<dl>
						<dt>글쓴이 ${news.user_id }</dt>
						<dd></dd>
					</dl>
					<dl>
						<dt>작성일</dt>
						<dd>${news.reg_date }</dd>
					</dl>
					<dl>
						<dt>조회</dt>
						<dd>${news.views }</dd>
					</dl>
					<dl style="position:absolute; right:0;">
						<dt><a onclick="chkDelete(${news.news_no}); return false;">삭제하기</a></dt>
					</dl>
				</div>
				<div class="cont" style="white-space:pre-wrap;">${news.content }</div>
				<div class="cont"><img src="${news.img }" alt="업로드 이미지"></div>
			</div>
			<div class="bt_wrap">
				<a href="list" class="on">목록</a>
				<a href="edit?news_no=${news.news_no }">수정</a>
			</div>
		</div>
	</div>
	<script>
		<c:if test="${param.error != null}">
			alert("${parm.error}");
		</c:if>
		<c:if test ="${error != null}">
			alert("${error}");
		</c:if>
	</script>
	<script type="text/javascript" src="./script.js"></script> <!-- 자바스크립트 -->
</body>
</html>
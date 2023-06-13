<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- 뷰포트 -->
<title>News Page</title>
<link rel="stylesheet" href="./css/style.css" />
<!-- css -->
 <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous"> 
<!--부트 스트랩 -->
</head>
<body>
	<c:if test="${newsList == null}">
		<jsp:forward page="list" />
	</c:if>
	<div class="wrap">
		<table class="news_list">
			<caption>
				<h1>뉴스게시판</h1>
			</caption>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<!-- for (News news : newsList)  -->
				<c:forEach var="news" items="${newsList}" varStatus="status">
					<tr>
						<td>${news.news_no }</td>
						<td class="title"><a href="view?news_no=${news.news_no }">${news.title }</a></td>
						<td>${news.user_id }</td>
						<td>${news.reg_date }</td>
						<td>${news.views }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="bt_wrap bt_list">
        <a href="write">글쓰기</a>
		</div>
		<div class="news_page">	
		<nav aria-label="Page navigation example" class="btn_list">
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
		</div>
	</div>
</body>
</html>
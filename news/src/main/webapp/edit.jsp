<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  <!-- jstl -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />  <!-- 뷰포트 -->
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />   <!-- css -->
</head>
<body>
	<div class="news_wrap">
		<div>
		<strong>뉴스게시판</strong>
		<p>수정중입니다.</p>
		</div>
		<div class="news_write_wrap">
		<form name="frm" method="post" action="update?news_no=${news.news_no }" enctype="multipart/form-data">
			<div class="news_write">
				<div class="title">
					<dl>
					<dt>제목</dt>
					<dd><input type="text" name="title" maxlength="50" placeholder="제목 입력" /></dd>
					</dl>
				</div>
				<div class="info">
					<dl>
					<dt>글쓴이</dt>
					<dd><input type="text" name= "user_id" maxlength="10" value="${news.user_id }"/></dd>
					</dl>
				</div>
				<div class="cont">
					<textarea name="content" >${news.content }</textarea>
				</div>
				<div style="padding-top:10px">
					<label style="font-size: 1.4rem; padding-right: 20px;" for="file">이미지 업로드</label>
					<input type="file" name="file" id="file"/>
				</div>
			</div>
		</form>
			<div class="bt_wrap">
			<a onclick="chkForm(); return false;" class= "on">수정</a>
			<a href="list">취소</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="./script.js"></script>
</body>
</html>
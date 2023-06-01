<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="member" class="ch07.Member"/>
    <!--Member member = new Member();  -->
    <jsp:setProperty property="*" name="member"/>
    <!--member.setEmail(request.getParameter("email"))  -->
    <!--member.setPhone(request.getParameter("phone"))  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=member.result() %>
</body>
</html>
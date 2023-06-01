<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%LocalDateTime.now(); %>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>	
<body>	
<h3>Current Time:
	<%java.util.Date cal=new java.util.Date(); %>
	<%= cal %>
 </h3>
 
</body>
</html>
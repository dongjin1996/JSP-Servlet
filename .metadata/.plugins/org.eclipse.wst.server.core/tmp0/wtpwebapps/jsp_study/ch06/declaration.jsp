<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
	String java = "Hello, Java Server Page";
	
	String getString(String data) {
		return data;
	}
	%>
	<h3>
	<%=getString(java) %>
	</h3>
	
</body>
</html>
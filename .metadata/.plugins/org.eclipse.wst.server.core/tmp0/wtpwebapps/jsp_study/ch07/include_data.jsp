<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  
	String number =request.getParameter("num");
	int num = Integer.parseInt(number); //정수형으로 바꾼다
	
	for(int i=1; i<=9; i++){		 
		out.println(num + "*" + i + "=" + (num*i)+ "<br/>");
		}
%> 

</body>
</html>
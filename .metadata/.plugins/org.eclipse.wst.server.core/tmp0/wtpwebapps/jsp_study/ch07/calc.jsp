<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- Calculator calc = new Calculator();
          
    	calc.setN1(request.getparameter("n1"));
    	calc.setN2(request.getparameter("n2"));
    	calc.setOP(request.getparameter("op"));
    -->
    
<jsp:useBean id="calc" class="ch07.Calculator" />

<!--calcForm.html에서 입력 받은 
	값 모두를 Calculator 클래스와 알맞는 필드에 넣어준다. 
  -->
<jsp:setProperty property="*" name="calc"/>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 결과: <%=calc.calc() %>
</body>
</html>
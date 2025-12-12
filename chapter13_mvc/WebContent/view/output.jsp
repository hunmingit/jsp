<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>현재 날짜(표현식) : <%=request.getAttribute("today") %></h1>
	<h1>현재 날짜(EL) : ${today}</h1>
	<!-- null 값으로 들어가게 되면 표현식은 null el은 빈값으로 출력 -->
	<hr>
	
	<h1>현재 시간(표현식) : <%=request.getAttribute("now") %></h1>
	<h1>현재 시간(EL) : ${now}</h1>
	
</body>
</html>








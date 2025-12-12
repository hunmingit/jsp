<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  1. 서블릿에서 전달 받는다고 치고 (자바) -->
<%-- 	<%
		String[] hobbies = {"노래", "춤", "음주"};
		request.setAttribute("HOBBIES", hobbies);
		request.getRequestDispatcher("Ex04_EL2.jsp").forward(request, response); //포워딩을하면 도착지에 1번 페이지 url로 보여진다.		
	%> --%>
	
	<form action="Ex04_EL2.jsp">
		취미 :
		<input type="checkbox" name="HOBBIES" value="노래">노래
		<input type="checkbox" name="HOBBIES" value="춤">춤
		<input type="checkbox" name="HOBBIES" value="음주">음주
		<br>
		<input type="submit" value="전송">
	
	</form>
</body>
</html>
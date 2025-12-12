<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 1. 기본 forEach 사용 예제 
		최소 값, 최대 값, 최소 값~ 최대 값 화면에 출력
		최소, 최대 값을 변수 저장
		
		2. 향상 forEach를 이용하여 종류들 출력
	-->
	 <c:set var = "num1" value="${param.num1 + 0}"/>
	 <c:set var = "num2" value="${param.num2 + 0}"/>
		
			<c:if test="${num1 >= num2}">
				<c:forEach var="i" begin="${num2}" end="${num1}" step="1">
					${i} <br>
				</c:forEach>
			</c:if>	 
			<c:if test="${num2 >= num1}">
				<c:forEach var="i" begin="${num1}" end="${num2}"  step="1">
					${i} <br>
				</c:forEach>
			</c:if>

	<c:forEach var="food" items="${paramValues.foods}">
		${food} <br>
	</c:forEach>
	

	
</body>
</html>
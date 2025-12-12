<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.member}"> 
			<button id="myPageBtn">마이페이지</button>
			<button id="LogoutBtn">로그아웃</button>
		</c:when>
		<c:otherwise>
			<button id="loginBtn">로그인</button>
		</c:otherwise>
	</c:choose>
	<button id="joinBtn">회원가입</button>
</body>
<script type="text/javascript" src="./js/main.js"></script>
</html>



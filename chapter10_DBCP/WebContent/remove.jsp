<%@page import="com.sun.source.tree.MemberReferenceTree"%>
<%@page import="org.joonzis.ex.MemberDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.joonzis.ex.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	//dao의 메소드한테 vo 객체를 전달
	MemberDAO dao = MemberDAO.getInstance();
	int result = dao.remove(id, pw);
	pageContext.setAttribute("result", result);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${result>0}">
			<script type="text/javascript">
				alert("데이터 삭제 성공");
				location.href = 'view_all.jsp';
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				alert("데이터 삭제 실패");
				location.href = 'view_all.jsp'; //
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>


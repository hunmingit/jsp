<%@page import="org.joonzis.vo.PersonVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>1. request 확인</h1>
		<ul>
			<li>이름 : <%=request.getAttribute("name") %> ${name}</li>
			<li>나이 : <%=request.getAttribute("age") %> ${age}</li>
			<li>주소 : <%=request.getAttribute("addr") %> ${addr}</li>
			<li>연락쳐 : <%=request.getAttribute("phone") %> ${phone}</li>
			<li>자기소개 : <%=request.getAttribute("self") %> ${self}</li>			
		</ul>
		<h1>2. session 확인</h1>
		<ul>
			<li>이름 : <%=session.getAttribute("name")%>${sessionScope.name}</li>
			<li>나이 : <%=session.getAttribute("age")%> ${sessionScope.age}</li>
			<li>주소 : <%=session.getAttribute("addr")%> ${sessionScope.addr}</li>
			<li>연락쳐 : <%=session.getAttribute("phone")%> ${sessionScope.phone}</li>
			<li>자기소개 : <%=session.getAttribute("self")%> ${sessionScope.self}</li>			
		</ul>
		<h1>3. vo 확인</h1>
		<ul>
			<li>이름 : ${vo.name} </li>
			<li>나이 : ${vo.age}</li>
			<li>주소 : ${vo.addr}</li>
			<li>연락쳐 : ${vo.phone}</li>
			<li>자기소개 : ${vo.self}</li>			
		</ul>
		<h1>4. map 확인</h1>
		<ul>
			<li>이름 :${map.name} </li>
			<li>나이 :${map.age} </li>
			<li>주소 :${map.addr} </li>
			<li>연락쳐 :${map.phone} </li>
			<li>자기소개 :${map.self} </li>
		</ul>				
	</div>
</body>
</html>
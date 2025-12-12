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
	<!-- 
		1. JSTL을 이용하여 평균 변수 생성(avr, grade)
		2. JSTL을 이용하여 합, 불 출력(60이상 합)
		3. 출력 데이터
		국어 : 00점
		영어 : 00점
		수학 : 00점
		평균 : 00점
		학점 : A
		합격여부 : 합격
	 -->
	 <c:set var = "kor" value="${param.kor + 0}"/>
	 <c:set var = "eng" value="${param.eng + 0}"/>
	 <c:set var = "mat" value="${param.mat + 0}"/>
	
 	<ul>
		<li>국어 : ${kor}</li> 
		<li>영어 : ${eng}</li>	 
		<li>수학 : ${mat}	</li> 
		<li>평균 : ${Math.round((eng+kor+mat)/3 * 100)/100.0}</li> 
		<li>학점 : 	<c:if test="${(eng+kor+mat)/3 > 90 }">
				A
			</c:if>	 
			<c:if test="${(eng+kor+mat)/3 > 80 && (eng+kor+mat)/3 <= 90}">
				B
			</c:if>	 
			<c:if test="${(eng+kor+mat)/3 > 70 && (eng+kor+mat)/3 <= 80 }">
				C
			</c:if>	 
			<c:if test="${(eng+kor+mat)/3 > 60 && (eng+kor+mat)/3 <= 70}">
				D
			</c:if>	 
			<c:if test="${(eng+kor+mat)/3 <= 60 }">
				F
			</c:if>	 </li>
		<li>합격여부 : <c:choose>
				<c:when test="${(eng+kor+mat)/3 >= 60}">
					합
				</c:when>
				<c:otherwise>
					불
				</c:otherwise>
			</c:choose> </li>
	 </ul>
</body>
</html>
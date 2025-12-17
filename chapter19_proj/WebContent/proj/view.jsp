<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 브라우저 서버 간 문자 깨짐 방지 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!--  jstl core 사용 (if, forEach, choose 등 제어문 담당 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><!-- 날짜, 숫자 포맷 fmt:formDate 사용 가능 jsp에서 java날짜 직접 다루지 않게 해주는 도구-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <%-- HTML 문서 인코딩을 UTF-8로 설정 --%>
<title>${board.title}</title>  <%-- 브라우저 탭에 표시될 제목 --%>
<link rel="stylesheet" href="css/view.css"> <%-- 글쓰기 페이지 전용 CSS 연결 --%>
<%-- 파비콘(브라우저 작은 아이콘) 설정 
실제 아이콘 파일을 가리키지 않는다(안 쓰면 콘솔에 favicon 에러 뜸) --%>
<link rel="shortcut icon" href="#">
</head>
<body>
	<h2>${board.title}</h2>
	
<div>
	<span>작성자: ${board.writer}</span> |
	<span>조회수: ${board.views}</span> |
	<span>
		<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM--dd"/><!-- 날짜 형식 변형 -->
	</span>
</div>

<hr>

<div>
	<pre>${board.content}</pre> <!--pre 본문 있는거 그대로 가져오겠따 -->
</div>

<hr>

<div>
	<button onclick="location.href='ProjController?cmd=boardList'">←목록으로</button>
		<!-- 글쓴이와 아이디가 같다면 or 어드민이면  -->
	    <c:if test="${sessionScope.returnVO.pId eq board.writer
                 || sessionScope.returnVO.role eq 'ADMIN'}">
        <button onclick="location.href='ProjController?cmd=editPage&bIdx=${board.bIdx}'">
            수정
        </button>
        <button type="button" onclick="deletePost(${board.bIdx})"> <!-- delete로 함수 이름 지정하면 안됨  delete는 자바스크립트 예약어  -->
            삭제
        </button>
    </c:if>
</div>

</body>
<script src="js/board.js"></script>
</html>
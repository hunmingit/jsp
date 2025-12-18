<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> <%-- JSP 페이지 설정: 스크립트 언어(java)/인코딩/콘텐츠 타입 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%--JSTL(Core) 태그 사용 선언 이게 있어야 <c:if>, <c:choose> 등 사용 가능--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <%-- HTML 문서 인코딩을 UTF-8로 설정 --%>
<title>글 수정</title>  <%-- 브라우저 탭에 표시될 제목 --%>
<link rel="stylesheet" href="css/write.css"> <%-- 글쓰기 페이지 전용 CSS 연결 --%>
<%-- 파비콘(브라우저 작은 아이콘) 설정 
실제 아이콘 파일을 가리키지 않는다(안 쓰면 콘솔에 favicon 에러 뜸) --%>
<link rel="shortcut icon" href="#">
</head>
<body>
	<h2>글 수정</h2>
	
	<form method="post" action="ProjController">
		<input type="hidden" name="cmd" value="edit">
		<input type="hidden" name="bIdx" value="${board.bIdx}">
		
		<div class="top-bar">
			<select name="category">
	            <option value="잡담" ${board.category eq '잡담' ? 'selected' : ''}>잡담</option>
	            <option value="질문" ${board.category eq '질문' ? 'selected' : ''}>질문</option>
	            <option value="정보" ${board.category eq '정보' ? 'selected' : ''}>정보</option>
			</select>
    		<div class="logo">
    		 	 <div> <img src="images/logo.png" width="30" style="margin-right:5px;"></div>
			</div>
		</div>
		
		<div>
			<input type="text" name="title" value="${board.title}" required> <%-- 반드시 채워져 있어야하는 필드는 required 사용//알럿으로 통일하는게 좋을 것 같긴한데.. --%>
		</div>
		<div>
			<textarea name="content" rows="10" required>${board.content}</textarea>
		</div>
		
		<!-- 관리자만 공지글 보이게 -->
		<c:if test="${sessionScope.returnVO.role eq 'ADMIN'}">
			<label>
				<input type="checkbox" name="isNotice" value="Y" ${board.isNotice eq 'Y' ? 'checked' : ''}>공지글
			</label>
		</c:if>
		
		<div>
			<button type="submit">수정</button> <!-- 이 form을 제출해라 url지정 x 그저 controller에 cmd = edit 보내기 -->
			<!-- 취소 시 글 상세보기 화면으로 이동 -->
			<button type="button" onclick="location.href='ProjController?cmd=view&bIdx=${board.bIdx}'">취소</button>
		</div>
	</form>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 브라우저 서버 간 문자 깨짐 방지 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!--  jstl core 사용 (if, forEach, choose 등 제어문 담당 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><!-- 날짜, 숫자 포맷 fmt:formDate 사용 가능 jsp에서 java날짜 직접 다루지 않게 해주는 도구-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="css/allList.css">
</head>
<body>

<div class="container">

  <!-- ===== 헤더 ===== -->
  <div class="board-header">
    <div class="logo">
      <div> <img src="images/logo.png" width="30" style="margin-right:10px;">
      		 <span class="logo-title">커뮤니티</span> </div>
    <form method="get" action="ProjController"> <!-- 게시글 목록 조회 요청 -->
      <input type="hidden" name="cmd" value="boardList">
      <select name="category">
        <option value="">전체</option>
        <option value="잡담">잡담</option>
        <option value="질문">질문</option>
        <option value="정보">정보</option>
      </select>
    </form>
    </div>

    <div class="user-info">
      <span>
        ${sessionScope.returnVO.pId} <!-- 로그인 성공 시 Controller가 session에 저장한 VO.pid가 노출됨 p_id로 받아서 오류 났었음 여기를 p_id로 바꿀까 하다 그냥 mapper 수정-->
        <c:if test="${sessionScope.returnVO.role eq 'ADMIN'}"> <!-- role이 'ADMIN' 이면 ㄱ(관리자) 표시 -->
          (관리자)
        </c:if>
      </span>
		<a href="ProjController?cmd=logout">로그아웃</a> <!-- 로그아웃 기능 -->
		<a href="ProjController?cmd=writePage" class="write-btn">글쓰기</a>
    </div>
  </div>

  <!-- ===== 게시판 테이블 ===== -->
  <table class="board-table">
    <thead>
      <tr>
        <th width="80">번호</th>
        <th>제목</th>
        <th width="150">글쓴이</th>
        <th width="100">등록일</th>
        <th width="80">조회</th>
        <th width="80">추천</th>
      </tr>
    </thead>

    <tbody>
      <!-- ===== 공지글 ===== -->
      <c:forEach var="post" items="${noticeList}">
        <tr class="notice"
            onclick="location.href='ProjController?cmd=view&bIdx=${post.bIdx}'">
          <td>공지</td>
          <td>🔥 ${post.title}</td>
          <td>${post.writer}</td>
          <td>
            <fmt:formatDate value="${post.regDate}" pattern="MM-dd"/>
          </td>
          <td>${post.views}</td>
          <td>${post.likes}</td>
        </tr>
      </c:forEach>

      <!-- ===== 일반글 ===== -->
      <c:forEach var="post" items="${postList}">
        <tr onclick="location.href='ProjController?cmd=view&bIdx=${post.bIdx}'">
          <td>${post.bIdx}</td>
          <td>${post.title}</td>
          <td>${post.writer}</td>
          <td>
            <fmt:formatDate value="${post.regDate}" pattern="MM-dd"/>
          </td>
          <td>${post.views}</td>
          <td>${post.likes}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <!-- ===== 페이지네이션 ===== -->
  <div class="paging">
    <c:if test="${pageMaker.prev}">
      <a href="?cmd=boardList&page=${pageMaker.startPage - 1}">이전</a>
    </c:if>

    <c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
      <a href="?cmd=boardList&page=${i}"
         class="${pageMaker.page == i ? 'active' : ''}"> <!-- 페이지가 i 와 같다면 active를 줘라 css a.active -->
        ${i}
      </a>
    </c:forEach>

    <c:if test="${pageMaker.next}">
      <a href="?cmd=boardList&page=${pageMaker.endPage + 1}">다음</a>
    </c:if>
  </div>

  <!-- ===== 검색 ===== -->
  <form method="get" action="ProjController" class="search-box">
    <input type="hidden" name="cmd" value="boardList">
    <select name="type">
      <option value="title">제목</option>
      <option value="writer">글쓴이</option>
    </select>
    <input type="text" name="keyword" placeholder="검색어 입력">
    <button type="submit">검색</button>
  </form>

</div>

</body>
</html>

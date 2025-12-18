<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> <%-- JSP 페이지 설정: 스크립트 언어(java)/인코딩/콘텐츠 타입 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%--JSTL(Core) 태그 사용 선언 이게 있어야 <c:if>, <c:choose> 등 사용 가능--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <%-- HTML 문서 인코딩을 UTF-8로 설정 --%>
<title>로그인/회원가입</title>  <%-- 브라우저 탭에 표시될 제목 --%>
<link rel="stylesheet" href="css/login.css"> <%-- 로그인 페이지 전용 CSS 연결 --%>
<%-- 파비콘(브라우저 작은 아이콘) 설정 
실제 아이콘 파일을 가리키지 않는다(안 쓰면 콘솔에 favicon 에러 뜸) --%>
<link rel="shortcut icon" href="#">
</head>
<body>
<div class="wrapper"><%-- 전체 레이아웃 감싸는 래퍼 박스 --%>
  <div class="box"><%-- 로그인/회원가입 박스 컨테이너 --%>

    <!-- 로고 -->
    <div style="text-align:center; margin-bottom: 20px;">
      <div style="display:flex; justify-content:center; align-items:center;">
         <div> <img src="images/logo.png" width="30" style="margin-right:10px;"></div>
        <span style="font-size:32px; color:#dc2626; letter-spacing:3px;">구구절절</span>
      </div>
    </div>

    <!-- 탭 -->
    <div class="tabs">
      <button id="loginTab" class="active">로그인</button> <%-- 로그인 탭: 기본 활성화 --%>
      <button id="joinTab">회원가입</button> <%-- 회원가입 탭 --%>
    </div>

    <!-- form -->
    <!-- 입력값을 서버로 보내는 컨테이너   document.getElementById("f").submit();-->
    <form id="f">
    	<div class="id-box"> <%-- 아이디 입력 + 중복확인 버튼 묶음 --%>
      <input type="text" name="pId" id="pId" maxlength="12" placeholder="아이디">
      <%-- 중복확인 버튼에 스타일줘서 로그인 탭에서는 노출안되게 --%>
      <button type="button" class="id-ck-btn" id="idCkBtn" style="display:none;">중복확인</button>
      </div>
      <input type="password" name="pPw" id="pPw" maxlength="16" placeholder="비밀번호">
      <input type="hidden" name="cmd" value="login" id="cmd">

      <!-- 회원가입 전용 -->
      <input type="password" name="pPwRe" id="pPwRe" placeholder="비밀번호 확인" 
            style="display:none;"> 
      
      <button type="button" class="login-sub-btn" id="loginSubBtn">로그인</button>
    </form>

    <!-- 관리자 계정 안내 -->
    <div id="adminInfo" style="text-align:center; margin-top:20px; font-size:13px; color:#ffffff;">
      관리자 계정: admin / admin
    </div>

  </div>
</div>
<!-- jstl문법 알럿 출력 -->
<c:choose>
  <c:when test="${not empty msg}">
    <script>
      alert("${msg}");
    </script>
  </c:when>

  <c:when test="${not empty sessionScope.msg}">
    <script>
      alert("${sessionScope.msg}");
    </script>
      <c:remove var="msg" scope="session"/>
  </c:when>
</c:choose>
</body>
<script type="text/javascript" src="js/login.js"></script>
</html>
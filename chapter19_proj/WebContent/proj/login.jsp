<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인/회원가입</title>
<link rel="stylesheet" href="css/login.css">
<link rel="shortcut icon" href="#">
</head>
<body>
<div class="wrapper">
  <div class="box">

    <!-- 로고 -->
    <div style="text-align:center; margin-bottom: 20px;">
      <div style="display:flex; justify-content:center; align-items:center;">
         <div> <img src="images/logo.png" width="30" style="margin-right:10px;"></div>
        <span style="font-size:32px; color:#dc2626; letter-spacing:3px;">커뮤니티</span>
      </div>
    </div>

    <!-- 탭 -->
    <div class="tabs">
      <button id="loginTab" class="active">로그인</button>
      <button id="joinTab">회원가입</button>
    </div>

    <!-- form -->
    <form id="f">
    	<div class="id-box">
      <input type="text" name="pId" id="pId" maxlength="12" placeholder="아이디">
      <button type="button" class="id-ck-btn" id="idCkBtn">중복확인</button>
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

</body>
<script type="text/javascript" src="js/login.js"></script>
</html>
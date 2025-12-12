<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
<script type="text/javascript"></script>
</head>
<body>
<body>
<div class="wrapper">
  <div class="box">

    <!-- 로고 -->
    <div style="text-align:center; margin-bottom: 20px;">
      <div style="display:flex; justify-content:center; align-items:center;">
        <!--컴 바뀌면 다시 적용할 예정-->
        <div img src="imges/logo" style="margin-right:5px;"></div>
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
      <input type="text" name="mId" id="mId" placeholder="아이디"> 
      <input type="password" name="mPw" id="mPw" placeholder="비밀번호">
      <input type="hidden" name="cmd" value="login" id="cmd">

      <!-- 회원가입 전용 -->
      <input type="password" name="mPwRe" id="mPwRe" placeholder="비밀번호 확인" 
            style="display:none;">
      
      <button type="button" class="submit-btn" id="submitBtn">로그인</button>
    </form>

    <!-- 관리자 계정 안내 -->
    <div id="adminInfo" style="text-align:center; margin-top:20px; font-size:13px; color:#ffffff;">
      관리자 계정: admin / admin
    </div>

  </div>
</div>



</body>


</html>
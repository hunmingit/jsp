<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%    
	Cookie cookie = null;
	String id = request.getParameter("id");
	String save_id = request.getParameter("save_id");
	
	if(save_id == null){
		//save_id를 하지 않겠다 -> 기존에 save_id가 쿠키가 있다면 삭제한다 
		Cookie[] cookieBox = request.getCookies();
		if(cookieBox != null && cookieBox.length > 0){
			for(int i=0; i<cookieBox.length; i++){
				if(cookieBox[i].getName().equals("save_id")){
					cookieBox[i].setMaxAge(0 * 60);
					response.addCookie(cookieBox[i]);
					break;
				}
			}
		}
	}else{//save_id를 하겠다.  아이디에 쿠키값이 저장됨
		cookie = new Cookie("save_id", id);
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="Ex05_id_check1.jsp">로그인 화면 돌아가기</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>  <!--파일 업로드 할꺼다 폼태그이면 무조건 포스트 그리고 enctype="multipart/form-data">-->
		<form action="upload.jsp" method="post" enctype="multipart/form-data">
			<p>업로더 <input type="text" name="uploader">  </p> 
			<p>업로더 <input type="file" name="filename">  </p>
			<input type="submit" value="업로드">
		</form>
	</div>
</body>
</html>
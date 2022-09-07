<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<hr>
	<form action="controller.do" method="post">
		<!-- 컨트롤러 Command 확인용 input -->
		<input type="hidden" name="command" value="loginCheck">
		
		<label for="memberID">아이디</label>
		<input type="text" id="memberID" name="memberID" /><br>
		<label for="memberPW">비밀번호</label>
		<input type="text" id="memberPW" name="memberPW" /><br>
		
		<input type="submit" value="로그인">
		<input type="button" value="취소" onclick="location.href='controller.do?command=index'">
	</form>
</body>
</html>
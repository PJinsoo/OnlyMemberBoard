<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<hr>
	<form action="../member.do" method="post">
		<input type="hidden" name="command" value="regiComplete">
		<label for="memberID">아이디</label>
		<input type="text" id="memberID" name="memberID" /><br>
		<label for="memberPW">비밀번호</label>
		<input type="text" id="memberPW" name="memberPW" /><br>
		<label for="memberNickname">닉네임</label>
		<input type="text" id="memberNickname" name="memberNickname" /><br>
		
		<input type="submit" value="가입하기">
		<input type="button" value="돌아가기" onclick='location.href="../member.do?command=index"'>
	</form>
</body>
</html>
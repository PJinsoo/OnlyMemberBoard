<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 정보 수정</h1>
	<hr>
	<form action="controller.do" method="post">
		<input type="hidden" name="command" value="memberUpdate">
		<input type="hidden" name="memberID" value="${memberID }">
		<label>아이디</label>
		<label>${memberID }</label><br>
		<label for="memberPW">비밀번호</label>
		<input type="text" id="memberPW" name="memberPW" value="${memberPW }"/><br>
		<label for="memberNickname">닉네임</label>
		<input type="text" id="memberNickname" name="memberNickname" value="${memberNickname }"/><br>
		
		<input type="submit" value="수정하기">
		<input type="button" value="돌아가기" onclick="location.href='controller.do?command=index'">
	</form>
</body>
</html>
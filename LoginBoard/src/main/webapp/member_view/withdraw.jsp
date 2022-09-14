<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	if (session.getAttribute("login") == null || !(boolean) session.getAttribute("login")) {
	%>

	<strong>잘못된 접근입니다.</strong>
	<br>
	<button onclick='location.href="../member.do?command=index"'>돌아가기</button>
	<br>

	<!-- 로그인 상태 -->
	<%
	} else if ((boolean) session.getAttribute("login")) {
	%>
	
	<form action="../member.do" method="post">
	<input type="hidden" name="command" value="leave">
	<input type="hidden" name="UID" value="${UID }">
		<h1>회원 탈퇴</h1>
		<hr>
		<strong>정말로 탈퇴하시겠습니까?</strong> <br> 
		<strong>탈퇴를 원하신다면 비밀번호를 입력해주세요.</strong> <br> 
		
		<input type="text" id="memberPW" name="memberPW" />
		<input type="submit" value="탈퇴하기"> 
		<input type="button" value="돌아가기" onclick="location.href='../member.do?command=index'">
	</form>
	<%
	}
	%>
</body>
</html>
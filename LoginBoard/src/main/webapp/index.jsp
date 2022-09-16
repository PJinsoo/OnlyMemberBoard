<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.lang.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<div>
		<!-- 비로그인 상태 -->
		<%
		if (session.getAttribute("login") == null || !(boolean) session.getAttribute("login")) {
		%>

		<strong>로그인이 필요합니다.</strong><br>
		<br>
		<button onclick='location.href="member.do?command=login"'>로그인</button>
		<button onclick='location.href="member.do?command=register"'>회원가입</button>

		<!-- 로그인 상태 -->
		<%
		} else if ((boolean) session.getAttribute("login")) {
		%>
		
		<strong>${memberNickname }님 환영합니다.</strong><br>
		<br>
		<button onclick='location.href="board.do?command=boardList"'>게시판 가기</button>
		<button onclick='location.href="member.do?command=memberUpdatePage&memberID=${memberID }"'>회원정보수정</button>
		<br>
		<button onclick='location.href="member.do?command=logout"'>로그아웃</button>
		<button onclick='location.href="member.do?command=withdraw&memberID=${memberID }"'>회원탈퇴</button>

		<%
		}
		%>

	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		System.out.println("비로그인 유저 접속");
		%>

		<strong>로그인이 필요합니다.</strong><br>
		<br>
		<button onclick='location.href="controller.do?command=login"'>로그인</button>
		<button onclick='location.href="controller.do?command=register"'>회원가입</button>

		<!-- 로그인 상태 -->
		<%
		} else if ((boolean) session.getAttribute("login")) {
		System.out.println("로그인 성공, 로그인 메뉴 출력");
		%>
		
		<strong>${memberNickname }님 환영합니다.</strong><br>
		<br>
		<button onclick='location.href="board.do?command=boardList"'>게시판
			가기</button>
		<br>
		<button
			onclick='location.href="controller.do?command=memberUpdatePage&memberID=${memberID }"'>회원정보수정</button>
		<button onclick='location.href="controller.do?command=logout"'>로그아웃</button>

		<%
		}
		%>

	</div>
</body>
</html>
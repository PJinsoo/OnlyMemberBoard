<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<!-- 비로그인 상태 -->
		<%
		if (session.getAttribute("login") == null || !(boolean) session.getAttribute("login")) {
		System.out.println("비로그인 유저 접속");
		%>

		<strong>잘못된 접근입니다.</strong><br>
		<button onclick='location.href="../controller.do?command=index"'>돌아가기</button><br>

		<!-- 로그인 상태 -->
		<%
		} else if ((boolean) session.getAttribute("login")) {
		System.out.println("새 글 쓰기 화면 출력");
		%>
		
		<h1>새 글 쓰기</h1> <hr>
		
		<form action="../board.do" method="post">
			<input type="hidden" name="command" value="insertExecute">
			<input type="hidden" name="UID" value="${UID }">
			<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="memberNickname" value="${memberNickname }" readonly></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><textarea rows="1" cols="60" name="boardTitle"></textarea></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="boardContent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="작성하기">
					<input type="button" value="취소" onclick="location.href='../board.do?command=boardList'">
				</td>
			</tr>
		</table>
		
		</form>
		
		<%
		}
		%>

	</div>
</body>
</html>
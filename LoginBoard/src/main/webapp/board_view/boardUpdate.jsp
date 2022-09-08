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
		System.out.println("비로그인 유저 접속");
	%>

	<strong>잘못된 접근입니다.</strong>
	<br>
	<button onclick='location.href="member.do?command=index"'>돌아가기</button>
	<br>

	<!-- 로그인 상태 -->
	<%
	} else if ((boolean) session.getAttribute("login")) {
	System.out.println("수정화면 출력");
	%>

	<h1>게시글 수정</h1>
	<hr>

	<form action="board.do" method="post">
		<input type="hidden" name="command" value="updateExecute">
		<input type="hidden" name="UID" value="${UID }">
		<input type="hidden" name="boardNo" value="${dto.boardNo }">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="memberNickname" value="${memberNickname }" readonly></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><textarea rows="1" cols="60" name="boardTitle">${dto.title }</textarea></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="boardContent">${dto.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="작성하기">
					<input type="button" value="취소" onclick="location.href='board.do?command=boardList'"></td>
			</tr>
		</table>

	</form>

	<%
	}
	%>
</body>
</html>
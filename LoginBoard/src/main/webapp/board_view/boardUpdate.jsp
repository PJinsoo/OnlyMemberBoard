<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<%
	if (session.getAttribute("login") == null || !(boolean) session.getAttribute("login")) {
	%>

	<strong>잘못된 접근입니다.</strong>
	<br>
	<button onclick='location.href="member.do?command=index"'>돌아가기</button>
	<br>

	<!-- 로그인 상태 -->
	<%
	} else if ((boolean) session.getAttribute("login")) {
	%>

	<h1>게시글 수정</h1>
	<hr>

	<!-- <form action="board.do" method="post">  -->
	<form id="updateForm" method="post">
		<!-- <input type="hidden" name="command" value="updateExecute">  -->
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
					<input type="button" id="updateBoard" value="작성하기">
					<input type="button" value="취소" onclick="location.href='board.do?command=boardList'"></td>
			</tr>
		</table>
	</form>

	<script>
	$("#updateBoard").click(function(){
		
		var form = $("#updateForm").serialize();
		
		$.ajax({
			url : "board.do?command=updateExecute",
			type : "post",
			data : form,
			success : function(data) {
				alert('게시글을 수정하셨습니다.');
				location.href='board.do?command=boardOne&boardNo=${dto.boardNo }';
			}
		});
		
	})
	</script>


	<%
	}
	%>
</body>
</html>
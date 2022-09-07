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
			System.out.println("인증되지 않은 유저의 접근");
		%>
		<strong>잘못된 접근입니다.</strong><br>
		<button onclick='location.href="controller.do?command=index"'>돌아가기</button><br>

		<!-- 로그인 상태 -->
		<%
		} else if ((boolean) session.getAttribute("login")) {
		%>
		
		<h1>게시판 보기</h1> <hr>
		<table border="1">
			<col width="70px">
			<col width="200px">
			<col width="70px">
			<col width="100px">
			
			<tr>
				<th>작성자</th>
				<td>${dto.memberNickname }</td>
				<th>작성일자</th>
				<td>${dto.postTime }</td>
			</tr>
			<tr>	
				<th>제목</th>
				<td>${dto.title }</td>
				<th>조회수</th>
				<td>${dto.viewCount }</td>
			</tr>
			<tr>	
				<th>내용</th>
				<td colspan="3"><textarea rows="10" cols="60">${dto.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="수정" onclick="location.href='board.do?command=boardUpdate&UID=${dto.UID }&boardNo=${dto.boardNo }'">
					<input type="button" value="삭제" onclick="location.href='board.do?command=boardDelete&UID=${dto.UID }&boardNo=${dto.boardNo }'">
					<input type="button" value="글 목록" onclick="location.href='board.do?command=boardList'">
				</td>
			</tr>
	</table>
		
		<%
		System.out.println("게시글 로딩 완료");
		}
		%>

	</div>
</body>
</html>
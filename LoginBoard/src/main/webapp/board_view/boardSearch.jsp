<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
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
		%>
		<strong>잘못된 접근입니다.</strong><br>
		<button onclick='location.href="member.do?command=index"'>돌아가기</button><br>

		<!-- 로그인 상태 -->
		<%
		} else if ((boolean) session.getAttribute("login")) {
		%>
		
		<h1>${searchWord }의 검색 결과</h1> <hr>
		<table border="1">
			<col width="50px"> 	<!-- 글 번호 -->
			<col width="200px"> <!-- 제목 -->
			<col width="100px">	<!-- 작성자 -->
			<col width="70px"> 	<!-- 조회수 -->
			<col width="100px"> <!-- 작성일자 -->
			<col width="50px"> 	<!-- 수정 -->
			<col width="50px"> 	<!-- 삭제 -->
			<tr>
				<td colspan="7">	
					<div align="right">
						<form action="board.do" method="post">
							<input type="hidden" name="command" value="search">
							<select name = "searchOption">
								<option value="searchTitle">제목</option>
								<option value="searchContent">내용</option>
								<option value="searchWriter">작성자</option>
								<option value="searchAll">전체</option>
							</select>
							<input type="text" name="searchWord">
							<input type="submit" value="검색">
						</form>
					</div>
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일자</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		<c:forEach var="search" items="${searchList }">
			<tr>
				<td><div align="center">${search.boardNo }</div></td>
				<td><a href="board.do?command=boardOne&boardNo=${search.boardNo }">${search.title }</a></td>
				<td><div align="center">${search.memberNickname }</div></td>
				<td><div align="center">${search.viewCount }</div></td>
				<td>${search.postTime }</td>
				<td><div align="center"><a href="board.do?command=boardUpdate&UID=${UID }&boardNo=${search.boardNo }">수정</a></div></td>
				<td><div align="center"><a href="board.do?command=boardDelete&UID=${UID }&boardNo=${search.boardNo }">삭제</a></div></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7">			
				<div align="right">
					<input type="button" value="게시판으로 돌아가기" onclick="location.href='board.do?command=boardList'">
					<input type="button" value="새 글 쓰기" onclick="location.href='board.do?command=boardInsert'">
				</div>
			</td>
		</tr>
	</table>
	
	<button onclick='location.href="member.do?command=index"'>내 페이지</button>
	<br>
		
		<%
		}
		%>

	</div>
</body>
</html>
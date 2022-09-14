<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 클릭한 게시글의 제목 -->
<title>${dto.title }</title>

<!-- 제이쿼리 라이브러리 불러오기 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>

	<script> //Ajax를 통한 추천기능 구현
		function recommend() {
			$.ajax({
				url : "board.do?command=recommend&boardNo=${dto.boardNo }",
				type : "get",
				data : {boardNo : ${dto.boardNo}},
				success:function(data) {
					//alert('게시글을 추천하셨습니다!');
					location.reload(); //자바스크립트의 새로고침 메서드
				}
			});
	 	}
	</script>

	<div>
		<!-- 비로그인 상태 -->
		<%
		if (session.getAttribute("login") == null || !(boolean) session.getAttribute("login")) {
		%>
		<strong>잘못된 접근입니다.</strong><br>
		<button onclick='location.href="member.do?command=index"'>돌아가기</button>
		<br>

		<!-- 로그인 상태 -->
		<%
		} else if ((boolean) session.getAttribute("login")) {
		%>

		<h1>게시글 보기</h1>
		<hr>
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
				<td colspan="3"><textarea rows="10" cols="60" readonly>${dto.content }</textarea></td>
			</tr>
			<tr>
				<!-- 추천 버튼, 클릭 시 recommend()에서 Ajax 수행 -->
				<td colspan="4" align="center">
				<input type="button" value="추천" onclick="recommend()" /> ${dto.recommend }
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right"><input type="button" value="수정"
					onclick="location.href='board.do?command=boardUpdate&UID=${UID }&boardNo=${dto.boardNo }'">
					<input type="button" value="삭제"
					onclick="location.href='board.do?command=boardDelete&UID=${UID }&boardNo=${dto.boardNo }'">
					<input type="button" value="글 목록"
					onclick="location.href='board.do?command=boardList'"></td>
			</tr>
		</table>

		<%
		}
		%>

	</div>
</body>
</html>
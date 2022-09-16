<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<!-- 비로그인 상태 -->
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

	<script> 
		//Ajax를 통한 추천기능 구현
		function recommend() {		
			$.ajax({
				//url : "comment.do?command=wow",
				url : "board.do?command=recommend",
				type : "get",
				data : {boardNo : ${dto.boardNo}},
				success:function(data) {
					//alert('게시글을 추천하셨습니다!');
					location.reload(); //자바스크립트의 새로고침 메서드
				}
			});
	 	}
		
		//Ajax를 통한 댓글 쓰기 구현
		function comment() {
			var commentParam = {
					UID : ${UID },
					boardNo : ${dto.boardNo },
					commentContent : $("#commentContent").val()
			}
			
			$.ajax({
				url : "comment.do?command=commentInsert",
				type : "get",
				data : commentParam,
				success : function(data) {
					alert('댓글을 작성하셨습니다.');
					location.reload(); //자바스크립트의 새로고침 메서드
				}
			});
	 	}
		
		//댓글 수정 버튼 클릭 시
		function commentUpdate(commentNo){
			alert("댓글 수정 미구현");
		}
		
		//댓글 삭제 버튼 클릭 시
		function commentDelete(commentNo, commentUID){
			var deleteParam = {
				UID : ${UID },
				comNo : commentNo,
				boardNo : ${dto.boardNo }
			}
			
			if(commentUID == ${UID }){
				$.ajax({
					url : "comment.do?command=commentDelete",
					type : "get",
					data : deleteParam,
					success : function(data) {
						alert("댓글이 삭제되셨습니다.");
						location.reload();
					}
				});
			}
			else{
				alert("해당 댓글의 작성자만 삭제할 수 있습니다!");
			}
		}
	</script>


	<div>
		<!-- 게시글 출력부 -->
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
				<td colspan="4" align="center"><input type="button" value="추천"
					onclick="recommend()" /> ${dto.recommend }</td>
			</tr>
			<tr>
				<td colspan="4" align="right"><input type="button" value="수정" onclick="location.href='board.do?command=boardUpdate&UID=${UID }&boardNo=${dto.boardNo }'">
					<input type="button" value="삭제"
					onclick="location.href='board.do?command=boardDelete&UID=${UID }&boardNo=${dto.boardNo }'">
					<input type="button" value="글 목록"
					onclick="location.href='board.do?command=boardList'"></td>
			</tr>
		</table>
	</div>

	<hr>

	<div>
		<!-- 댓글 출력부 -->
		<table>
			<col width="70px">
			<col width="200px">
			<col width="100px">
			<col width="70px">
			<col width="70px">

			<tr bgcolor=#F4EFE4>
				<th colspan="5" align="center">댓글창</th>
			</tr>

			<tr bgcolor=#F4EFE4>
				<th align="center">작성자</th>
				<th align="center">댓글</th>
				<th align="center">작성일자</th>
				<th align="center">수정</th>
				<th align="center">삭제</th>
			</tr>
			
			<!-- 댓글 칸 -->
			<c:forEach var="commentDTO" items="${commentList }">
				<tr id="updateField" bgcolor=#F4EFE4>
					<td align="center"><b>${commentDTO.memberNickname }</b></td>
					<td>${commentDTO.commentContent }</td>
					<td align="center">${commentDTO.commentTime }</td>
					<td align="center">
						<a href="#" onclick="commentUpdate(${commentDTO.commentNo})">수정</a>
					</td>
					<td align="center">
						<a href="#" onclick="commentDelete(${commentDTO.commentNo}, ${commentDTO.UID})">삭제</a>
					</td>
				</tr>
			</c:forEach>

			<!-- 댓글 작성칸 -->
			<tr bgcolor=#F4EFE4>
				<td align="center"><b>${memberNickname }</b></td>
				<td colspan="3"><textarea rows="1" cols="50" id="commentContent"></textarea></td>
				<td align="center"><input type="button" value="댓글 쓰기" onclick="comment()" /></td>
			</tr>
		</table>

		<%
		}
		%>

	</div>
</body>
</html>
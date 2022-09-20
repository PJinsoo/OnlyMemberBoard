<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 쓰기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
		
		<h1>새 글 쓰기</h1> <hr>
		
		<!-- <form action="../board.do" method="post"> -->
		<!-- <input type="hidden" name="command" value="insertExecute">  -->
		
		<form id="insertForm" method="post">
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
					
						<!-- submit 형태로 controller에게 데이터 전송 
						<input type="submit" value="작성하기"> -->
						
						<!-- Ajax로 controller에게 데이터 전송 -->
						<input type="button" id="insertBoard" value="작성하기">
						<input type="button" value="취소" onclick="location.href='../board.do?command=boardList'">
					</td>
				</tr>
			</table>
		</form>
		
		<script>
		
		//ajax로 게시글 작성 구현
		$("#insertBoard").click(function(){			
			
			//form 태그 내부의 데이터를 serialize()로 정리하여 저장
			var form = $("#insertForm").serialize()
			
			$.ajax({
				url : "../board.do?command=insertExecute",
				type : "post",
				data : form,
				success : function(data) {
					alert('새로운 게시글을 등록하셨습니다.');
					location.href='../board.do?command=boardList';
				}
			});
		})
		
		</script>
		
		
		
		<%
		}
		%>

	</div>
</body>
</html>
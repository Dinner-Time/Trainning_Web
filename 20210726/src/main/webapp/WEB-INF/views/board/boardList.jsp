<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 전체 보기</title>
</head>
<body>
	<div align="center">
		<h1>게시판 전체 목록</h1>
		<table border="1">
			<tr>
				<th width="70">번호</th>
				<th width="300">제목</th>
				<th width="150">작성자</th>
				<th width="150">작성일자</th>
				<th width="70">조회수</th>
			</tr>
			<c:forEach var="board" items="${boards }">
				<tr onmouseover="this.style.background='yellow'" onmouseout="this.style.background='white'" onclick="getRecord(${board.bId})">
					<td align="center">${board.bId }</td>
					<td>${board.bTitle }</td>
					<td align="center">${board.bWriter }</td>
					<td align="center">${board.bDate }</td>
					<td align="center">${board.bHit }</td>
				</tr>
			</c:forEach>
		</table>
		<button style="margin: 10px;" type="button" onclick="location.href = 'home.do'">HOME</button>
		<button style="margin: 10px;" type="button" onclick="location.href = 'insertForm.do'">글 작성</button>
	</div>
	<div>
		<form style="display: none" id="frm" name="frm"action="boardSelect.do" method="post">
			<input id="bId" name="bId">
		</form>
	</div>
	<script type="text/javascript">
		function getRecord(n){
			frm.bId.value = n;
			frm.submit();
		}
	</script>
</body>
</html>
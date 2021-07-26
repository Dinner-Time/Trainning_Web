<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>
	<div align="center">
		<table border="1">
			<tr>
				<th width="50" align="center">번호</th>
				<td width="70" align="center">${board.bId }</td>
				<th width="100" align="center">작성자</th>
				<td width="150" align="center">${board.bWriter }</td>
				<th width="100" align="center">작성일자</th>
				<td width="150" align="center">${board.bDate }</td>
				<th width="100" align="center">조회수</th>
				<td width="70" align="center">${board.bHit }</td>
			</tr>
			<tr>
				<th align="center" height="40">제목</th>
				<td colspan="7">${board.bTitle }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td height="200" colspan="7">${board.bContent }</td>
			</tr>
		</table>
		<button style="margin: 10px;" type="button" onclick="location.href = 'boardList.do'">뒤로가기</button>
		<form style="display: inline-block" id="frmUp" name="frmUp" action="updateForm.do" method="post">
			<input type="hidden" id="bId" name="bId" value="${board.bId }">
			<input type="hidden" id="bTitle" name="bTitle" value="${board.bTitle }">
			<input type="hidden" id="bContent" name="bContent" value="${board.bContent }">
			<button style="margin: 10px;" type="submit">글 수정</button>
		</form>
		<form style="display: inline-block" id="frmDel" name="frmDel" action="deleteBoard.do" method="post">
			<input type="hidden" id="bId" name="bId" value="${board.bId }">
			<button style="margin: 10px;" type="submit">글 삭제</button>
		</form>
	</div>
</body>
</html>
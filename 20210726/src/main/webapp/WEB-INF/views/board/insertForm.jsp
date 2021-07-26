<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 작성</title>
</head>
<body>
	<div align="center">
		<form action="insertBoard.do" id="frm" name="frm" method="post">
			<table border="1">
				<tr>
					<th width="100" align="center">작성자</th>
					<td width="150" align="center"><input type="text" id="bWriter"
						name="bWriter" required="required"></td>
					<th width="100" align="center">작성일</th>
					<td width="150" align="center"><input type="date" id="bDate"
						name="bDate"></td>
				</tr>
				<tr>
					<th align="center">제목</th>
					<td colspan="3"><input type="text" id="bTitle" name="bTitle"
						required="required"></td>
				</tr>

				<tr>
					<th>내용</th>
					<td colspan="3"><textarea rows="7" cols="58"
							style="resize: none;" id="bContent" name="bContent"
							required="required"></textarea></td>
				</tr>
			</table>
			<button style="margin: 10px;" type="button"
				onclick="location.href = 'boardList.do'">뒤로 가기</button>
			<button style="margin: 10px;" type="submit">등록</button>
			<button style="margin: 10px;" type="reset">취소</button>
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form action="updateBoard.do" id="frm" name="frm" method="post">
			<table border="1">
				<tr>
					<th align="center">제목</th>
					<td colspan="3"><input type="text" id="bTitle" name="bTitle"
						required="required" value="${bTitle }"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3"><textarea rows="7" cols="58"
							style="resize: none;" id="bContent" name="bContent"
							required="required">${bContent }</textarea></td>
				</tr>
			</table>
			<input type="hidden" id="bId" name="bId" value=${bId }>
			<button style="margin: 10px;" type="button" onclick="location.href = 'boardList.do'">뒤로 가기</button>
			<button style="margin: 10px;" type="submit">수정</button>
			<button style="margin: 10px;" type="reset">취소</button>
		</form>
	</div>
</body>
</html>
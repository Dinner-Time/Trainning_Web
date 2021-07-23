<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회</title>
</head>
<body>
	<form id="frm" name="frm" action="memberSearch.do" method="post">
		<table border="1">
			<tr>
				<th>조회할 회원 아이디</th>
				<td><input type="text" id="id" name="id"></td>
				<td><button type="submit">검색</button></td>
			</tr>
		</table>
	</form>
	<button type="button" onclick="location.href='main.do'">..HOME</button>
</body>
</html>
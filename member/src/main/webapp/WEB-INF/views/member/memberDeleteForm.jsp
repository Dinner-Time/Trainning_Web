<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="frm" name="frm" action="memberDelete.do" method="post">
		<table border="1">
			<tr>
				<th>탈퇴할 회원 아이디</th>
				<td><input type="text" id="id" name="id"></td>
				<td><button type="submit">탈퇴</button></td>
			</tr>
		</table>
	</form>
	<button type="button" onclick="location.href='main.do'">..HOME</button>
</body>
</html>
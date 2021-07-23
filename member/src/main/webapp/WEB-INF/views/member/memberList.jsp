<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	th,
	td{
		padding: 5px 10px;
	}
	button{
		margin-top: 20px;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>회원목록 리스트</h1>
		<hr>
		<table border="1">
			<tr>
				<th>id</th>
				<th>password</th>
				<th>name</th>
				<th>age</th>
				<th>hobby</th>
			</tr>
			<c:forEach var="member" items="${list}">
			<tr>
				<td align="center">${member.id}</td>
				<td align="center">${member.password}</td>
				<td align="center">${member.name}</td>
				<td align="center">${member.age}</td>
				<td align="center">${member.hobby}</td>
			</tr>
			</c:forEach>
		</table>
		<button type="button" onclick="location.href='main.do'">..HOME</button>
	</div>

</body>
</html>
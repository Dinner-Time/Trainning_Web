<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<jsp:include page="../home/header.jsp"></jsp:include>
	<h1> ${name }님, 환영합니다!! (접근권한 : ${author })</h1>
</body>
</html>
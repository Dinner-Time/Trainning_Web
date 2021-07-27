<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<body>
	<header>
		<ul>
			<li><a class="active" href="home.do">Home</a></li>
			<c:if test="${empty name }">
				<li><a href="loginForm.do">Login</a></li>
			</c:if>
			<c:if test="${not empty name }">
				<li><a href="logout.do">Logout</a></li>
				<li><a href="home.do">Contact</a></li>
				<li><a href="home.do">Product</a></li>
				<li><a href="home.do">Service</a></li>
				<li><a href="home.do">About</a></li>
				<c:if test="${author eq 'ADMIN'}">
					<li><a href="home.do">Members</a></li>
				</c:if>
			</c:if>
		</ul>
		<div class="hidden"></div>
	</header>
</body>
</html>
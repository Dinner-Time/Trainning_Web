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
	<h1>회원 가입</h1>
	<form id="frm" name="frm" action="memberSignUp.do" method="post">
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" name="id" id="id" placeholder="아이디 입력"
					required="required"></td>
			</tr>
			<tr>
				<th>PASSWORD</th>
				<td><input type="password" name="password" id="password" placeholder="비밀번호 입력" required="required"></td>
			</tr>
			<tr>
				<th>PASSWORD CHECK</th>
				<td><input type="password" name="passcheck" id="passcheck" placeholder="비밀번호 확인" required="required"></td>
			</tr>
			<tr>
				<th>NAME</th>
				<td><input type="text" name="name" id="name" placeholder="이름 입력" required="required"></td>
			</tr>
			<tr>
				<th>AGE</th>
				<td><input type="number" name="age" id="age" placeholder="나이 입력" required="required"></td>
			</tr>
			<tr>
				<th>HOBBY</th>
				<td>
					<label><input type="checkbox" name="hobbys" id="hobbys" value="요리">요리</label><br>
					<label><input type="checkbox" name="hobbys" id="hobbys" value="야구">야구</label><br>
					<label><input type="checkbox" name="hobbys" id="hobbys" value="기타">기타</label><br>
					<label><input type="checkbox" name="hobbys" id="hobbys" value="독서">독서</label><br>
					<label><input type="checkbox" name="hobbys" id="hobbys" value="게임">게임</label><br>
				</td>
			</tr>
		</table>
		<button type="submit">등록</button>
		<button type="reset">가입 취소</button>
	</form>
	<button type="button" onclick="location.href='main.do'">..HOME</button>
</body>
</html>
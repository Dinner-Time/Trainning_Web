<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/loginBox.css">
<script type="text/javascript">
	function textEffect() {
		const inputText = document.querySelectorAll('input');
		const labelText = document.querySelectorAll('input ~ label');

		for (let i = 0; i < inputText.length; i++) {
			if (inputText[i].value != "") {
				console.log(labelText[i]);
				labelText[i].classList.remove("active");
				labelText[i].classList.add("inactive");
			} else {
				labelText[i].classList.remove("inactive");
				labelText[i].classList.add("active");
			}
		}
	}
</script>
</head>
<body>
	<jsp:include page="../home/header.jsp"></jsp:include>
	<div class="container">
		<div class="box">
			<form action="login.do" id="frm" name="frm" method="post">
				<div class="loginBox">
					<input type="text" id="id" name="id" required="" onchange="textEffect()"> 
					<label>Username</label>
				</div>
				<div class="loginBox">
					<input type="password" id="password" name="password" required="" onchange="textEffect()"> 
					<label>Password</label>
				</div>
				<button type="submit">login</button>
				<button type="reset">back</button>
			</form>
		</div>
	</div>
</body>
</html>
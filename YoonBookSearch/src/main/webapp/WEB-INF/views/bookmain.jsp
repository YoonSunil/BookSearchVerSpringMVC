<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="utf-8" />
<link href="<c:url value="/resources/css/default.css" />" rel="stylesheet"
	type="text/css" />
<script>
	function validateForm() {
		var uid = document.getElementById("login");
		var upw = document.getElementById("puser");
		var error = document.getElementsByClassName("error")[0];

		 if (uid.value == "" && upw.value != "") {
			error.innerHTML = "ユーザーIDを入力してください。";
			error.style.visibility = "visible";
			/* 			document.getElementById().focus(); */
			window.setTimeout(viewError, 2000);
			return false;
		}else if (upw.value == "" && uid.value != "") {
			error.innerHTML = "パスワードを入力してください。";
			error.style.visibility = "visible";
			/* 			document.getElementById().focus(); */
			window.setTimeout(viewError, 2000);
			return false;
		} else if (uid.value == "" || upw.value == "") {
			error.innerHTML = "ユーザーIDを入力してください。";
			error.innerHTML+= '<br>'
			error.innerHTML+="パスワードを入力してください。"
			error.style.visibility = "visible";
			/* 			document.getElementById().focus(); */
			window.setTimeout(viewError, 2000);
			return false;
		}/* else if(uid.value != ${userDTO.userId}){
			error.innerHTML = "存在しません"
			return false;
		} */else {
			return true;
		}

		return false;
	}

	function viewError() {
		document.getElementsByClassName("error")[0].style.visibility = "hidden"
	}
</script>
<title>YoonBookSearch</title>
</head>
<body>
	<div>
		<h1>ログイン</h1>
		<hr style="border: double 5px red;">
	</div>

	<div style="max-width: 500px; margin: 100 auto;" id="userlogin"
		class="userlogin">
		<form action="<c:url value="/bookmain" />"
			onsubmit="return validateForm()" method="post" id="bookmain">
			<p class="error">error</p>
			<label for="loginId">ユーザーID</label> <input
				style="width: 80%; height: 30px; padding: 10px;" type="text"
				class="loginId" id="login" name="userId"> <br> <label
				for="upw">パスワード</label> <input
				style="width: 80%; height: 30px; padding: 10px;" type="password"
				class="loginPass" id="puser" name="userPw"> <br>
			<button type="submit" id="loginBtn" class="loginBtn">ログイン</button>
		</form>
	</div>
</body>
</html>

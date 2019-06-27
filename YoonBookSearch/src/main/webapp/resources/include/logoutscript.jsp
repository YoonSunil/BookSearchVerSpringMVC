<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		function userOut() {
			if (confirm("ログアウトします。よろしいですか？")) {
				location.href = "/software_s/"
			}
		}
	</script>
	<div style='width: 100%; height: 64px; border: 2px solid gold;'>
		<p style="float: left; padding: 5px;">Book Search</p>
		<a style="float: right; padding: 5px; cursor: grab;"
			onclick="return userOut();">ログアウト</a>
	</div>
	<br>

</body>
</html>
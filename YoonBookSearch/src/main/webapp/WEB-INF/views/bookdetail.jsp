<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="utf-8" />
<link href="<c:url value="/resources/css/detail.css" />" />
<title>YoonBookSearch</title>
<%@ include file="/resources/include/logoutscript.jsp" %>
<h1>図書詳細</h1>
<hr style="border: double 5px red;">
<script>
	if ("${msg}" != "") {
		alert("${msg}");
	}

	function clickBtn(btn) {
		var form = document.form;

		switch (btn.innerText) {
		case '更新':
			document.form.setAttribute("action",
					"${pageContext.request.contextPath}/bookupload/${data.bookId}");
			document.form.setAttribute("method", "get");
			form.submit();
			return false;
		case '削除':
			if (confirm("図書情報を削除します。よろしいですか？")) {
				document.form.setAttribute("action",
						"${pageContext.request.contextPath}/delete");
				document.form.setAttribute("method", "post");
				form.submit();
			}
			return false;
		case '戻る':
			location.href = '${pageContext.request.contextPath}/booklist/1';
		}
	}
</script>
</head>
<body>
	<div class="container">
		<form>
			<div class="row">
				<div class="wd20">
					<h4>ISBN:</h4>
				</div>
				<div class="wd80">
					<h4>${data.isbn}</h4>
				</div>
			</div>
			<div class="row">
				<div class="wd20">
					<h4>JANコード:</h4>
				</div>
				<div class="wd80">
					<h4>${data.janCode}</h4>
				</div>
			</div>
			<div class="row">
				<div class="wd20">
					<h4>タイトル:</h4>
				</div>
				<div class="wd80">
					<h4>${data.title}</h4>
				</div>
			</div>
			<div class="row">
				<div class="wd20">
					<h4>著者名 :</h4>
				</div>
				<div class="wd80">
					<h4>${data.writer}</h4>
				</div>
			</div>
			<div class="row">
				<div class="wd20">
					<h4>出版社名:</h4>
				</div>
				<div class="wd80">
					<h4>${data.pubCom}</h4>
				</div>
			</div>
			<div class="row">
				<div class="wd20">
					<h4>出版年月日:</h4>
				</div>
				<div class="wd80">
					<h4>${data.pubDate}</h4>
				</div>
			</div>
		</form>
		<form name="form">
			<input type="hidden" name="bookId" value="${data.bookId}">
		</form>
		<div class="wd100">
			<button class="pink" onclick="clickBtn(this)">削除</button>
			<button class="blue right" onclick="clickBtn(this)">更新</button>
			<button class="grey right" onclick="clickBtn(this)">戻る</button>
		</div>
	</div>
</body>
</html>

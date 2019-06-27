<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="utf-8" />
<link href="<c:url value="/resources/css/list.css" />" rel="stylesheet"
	type="text/css" />
<title>YoonBookSearch</title>
<%@ include file="/resources/include/logoutscript.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/include/defualt.js"></script>
<script>

	function clickBtn(btn) {
		switch (btn.innerText) {
		case '更新':
			if (validateForm(document.form)) {
				if (confirm("入力された情報で更新します。よろしいですか？")) {
					document.form.setAttribute("action",
							"${pageContext.request.contextPath}/bookupload/${bookId}");
					document.form.setAttribute("method", "post");
					form.submit();
				}
			}
			return false;
		case 'キャンセル':
			if (confirm("入力された内容をキャンセルし、図書詳細画面に戻ります。\nよろしいですか？")) {
				history.go(-1);
			}
		}
	}
</script>

</head>
<body>
	<h2>図書更新</h2>
	<div class="container">
		<form name="form">
			<p class="error">入力エラー ：○○○○は必ず入力してください。</p>
			<div class="row">
				<div class="wd25">
					<label for="isbn"> ISBN </label>
					<strong>*</strong>
				</div>
				<div class="wd75">
					<input type="number" name="isbn"  id="isbn" onkeyup='getByteLength(this, 13)' value="${data.isbn}">
					<h5>半角英数字</h5>
					<p>半角英数字 ※ハイフン（-）は必要ありません。</p>
				</div>
			</div>
			<div class="row">
				<div class="wd25">
					<label for="janCode">JANコード</label>
				</div>
				<div class="wd75">
					<input type="number" name="janCode" id="janCode" onkeyup='getByteLength(this, 13)' value="${data.janCode}">
					<h5>半角英数字</h5>
				</div>
			</div>
			<div class="row">
				<div class="wd25">
					<label for="title"> タイトル </label>
					<strong>*</strong>
				</div>
				<div class="wd75">
					<input type="text" name="title" id="title" onkeyup='getByteLength(this, 130)' value="${data.title}">
				</div>
			</div>
			<div class="row">
				<div class="wd25">
					<label for="writer"> 著者名 </label>
					<strong>*</strong>
				</div>
				<div class="wd75">
					<input type="text" name="writer" id="writer" onkeyup='getByteLength(this, 80)' value="${data.writer}">
				</div>
			</div>
			<div class="row">
				<div class="wd25">
					<label for="publisher"> 出版社名 </label>
					<strong>*</strong>
				</div>
				<div class="wd75">
					<input type="text" name="pubCom" id="pubCom" onkeyup='getByteLength(this, 120)' value="${data.pubCom}">
				</div>
			</div>
			<div class="row">
				<div class="wd25">
					<label for="date"> 出版年月日 </label>
					<strong>*</strong>
				</div>
				<div class="wd75">
					<input type="date" name="pubDate" id="pubDate" value="${data.pubDate}">
					<p>2018年1月9日の場合「2018/01/09」と入力して下さい。</p>
				</div>
			</div>
			<div class="row">
				<div class="wd25">
					<label for="usr">
						<strong>*</strong> 必須入力項目
					</label>
				</div>
				<div class="wd75"></div>
			</div>
			<input type="hidden" name="bookId" value="${data.bookId}">
		</form>
		<div class="wd100">
			<button class="regist right" onclick="clickBtn(this)">更新</button>
			<button class="cancel right" onclick="clickBtn(this)">キャンセル</button>
		</div>
	</div>
</body>
</html>

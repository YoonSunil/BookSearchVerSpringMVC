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
<%@ include file="/resources/include/logoutscript.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/include/defualt.js"></script>
<h1>図書検索・一覧</h1>
<hr style="border: double 5px red;">
<script>
	if ("${msg}" != "") {
		alert("${msg}");
	}
	window.onload = function() {
		if("${status}" == "search"){
			document.getElementsByName("isbn")[0].value = "${searchDTO.isbn}";
			document.getElementsByName("janCode")[0].value = "${searchDTO.janCode}";
			document.getElementsByName("title")[0].value = "${searchDTO.title}";
			document.getElementsByName("writer")[0].value = "${searchDTO.writer}";
			document.getElementsByName("pubCom")[0].value = "${searchDTO.pubCom}";
			document.getElementsByName("startDate")[0].value = "${searchDTO.startDate}";
			document.getElementsByName("endDate")[0].value = "${searchDTO.endDate}";

			for (let radio of document.getElementsByName("matchTitle")) {
				radio.checked = (radio.value == "${searchDTO.matchTitle}");
			}
			for (let radio of document.getElementsByName("matchWriter")) {
				radio.checked = (radio.value == "${searchDTO.matchWriter}");
			}
			for (let radio of document.getElementsByName("matchPubCom")) {
				radio.checked = (radio.value == "${searchDTO.matchPubCom}");
			}
		}
	}
	function clickBtn(btn) {
		switch (btn.innerText) {
		case '検索':
			var isbn = document.getElementsByName("isbn")[0];
			var janCode = document.getElementsByName("janCode")[0];

			isbn.value=(isbn.value == "") ? 0 : isbn.value;
			janCode.value=(janCode.value == "") ? 0: janCode.value;

			document.form.setAttribute("action",
					"${pageContext.request.contextPath}/search/1");
			document.form.setAttribute("method", "get");
			form.submit();
			return false;
		case '新規登録':
			location.href = '${pageContext.request.contextPath}/bookinsert';
		}
	}
	function page(idx){
		switch("${status}"){
			case "list":
				location.href = '${pageContext.request.contextPath}/booklist/'+idx;
				break;
			case "search":
				form.setAttribute("action", "${pageContext.request.contextPath}/search/" + idx);
				form.setAttribute("method", "get");
				form.submit();
				break;
		}

	}
</script>
</head>
<body>

	<div class="container">
		<form name="form">
			<div class="row">
				<div class="wd50">
					<div class="wd25">
						<label for="isbn">ISBN</label>
					</div>
					<div class="wd75">
						<input class="wd80" type="number" name="isbn" id="isbn"
							onkeyup='getByteLength(this, 13)'>
						<h5>半角英数字</h5>
					</div>
				</div>
				<div class="wd50">
					<div class="wd25">
						<label for="janCode">JANコード</label>
					</div>
					<div class="wd75">
						<input class="wd50" type="number" name="janCode" id="janCode"
							onkeyup='getByteLength(this, 13)'>
						<h5>半角英数字</h5>
						<button class="right" type="button" onclick="clearForm(this.form)">条件クリア</button>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="wd50">
					<div class="wd25">
						<label for="title">タイトル</label>
					</div>
					<div class="wd75">
						<input class="wd100" type="text" name="title" id="title"
							onkeyup='getByteLength(this, 130)'>
						<div>
							<input type="radio" name="matchTitle" value="full" checked>
							完全一致 <input type="radio" name="matchTitle" value="forward">
							前方一致 <input type="radio" name="matchTitle" value="partial">
							部分一致
						</div>
					</div>
				</div>
				<div class="wd50">
					<div class="wd25">
						<label for="writer">著者名</label>
					</div>
					<div class="wd75">
						<input class="wd100" type="text" name="writer" id="writer"
							onkeyup='getByteLength(this, 80)'>
						<div>
							<input type="radio" name="matchWriter" value="full" checked>
							完全一致 <input type="radio" name="matchWriter" value="forward">
							前方一致 <input type="radio" name="matchWriter" value="partial">
							部分一致
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="wd50">
					<div class="wd25">
						<label for="publisher">出版社名</label>
					</div>
					<div class="wd75">
						<div>
							<input class="wd100" type="text" name="pubCom" id="pubCom"
								onkeyup='getByteLength(this, 120)'> <input type="radio"
								name="matchPubCom" value="full" checked> 完全一致 <input
								type="radio" name="matchPubCom" value="forward"> 前方一致 <input
								type="radio" name="matchPubCom" value="partial"> 部分一致
						</div>
					</div>
				</div>
				<div class="wd50">
					<div class="wd25">
						<label for="pubDate">出版年月</label>
					</div>
					<div class="wd75 displayflex">
						<input class="wd40" type="date" name="startDate" id="startDate"
							onkeyup='getByteLength(this, 6)'>
						<h5 class="wd20">～</h5>
						<input class="right wd40" type="date" name="endDate" id="endDate"
							onkeyup='getByteLength(this, 6)'>
					</div>

				</div>
			</div>
		</form>
		<div class="wd100">
			<button class="regist" onclick="clickBtn(this)">新規登録</button>
			<button class="search right" onclick="clickBtn(this)">検索</button>
		</div>
		<p class="right">${pageMaker.totalCount}件中
			${pageMaker.startPage}件～${pageMaker.startPage+20}件表示中</p>
		<div style="width: 100%; float: Center; margine: 200px;">
			<table>
				<thead class="thead-dark">
					<tr>
						<th>No</th>
						<th>タイトル</th>
						<th>著者名</th>
						<th>出版社名</th>
						<th>出版年月日</th>
						<th>ISBN</th>
						<th>JANコード</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${data}" var="data" varStatus="i">
						<tr>
							<td>${i.count}</td>
							<td><a
								href="${pageContext.request.contextPath}/bookdetail/${data.bookId}">${data.title}</a></td>
							<td>${data.writer}</td>
							<td>${data.pubCom}</td>
							<td>${data.pubDate}</td>
							<td>${data.isbn}</td>
							<td>${data.janCode}</td>
						</tr>
					</c:forEach>
			</table>

			<div class="center wd100">
				<div class="pagination">
					<a onclick="page(1)">&laquo;</a> <a style="text-decoration: none;"
						href="javascript:page(${pageMaker.getFrontPage()});">&laquo;</a>

					<!-- 페이지 숫자 표시 -->
					<c:forEach begin="${pageMaker.getStartPage()}"
						end="${pageMaker.endPage}" var="idx">
						<a style="text-decoration: none;" href="javascript:page(${idx});">${idx}</a>
					</c:forEach>

					<!-- 오른쪽 화살표 -->
					<a style="text-decoration: none;"
						href="javascript:page(${pageMaker.getNextPage()});">&raquo;</a> <a
						style="text-decoration: none;"
						href="javascript:page(${pageMaker.getMaxPage()});">&raquo;</a>

				</div>
			</div>
		</div>
</body>
</html>

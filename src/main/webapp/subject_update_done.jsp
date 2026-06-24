<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html lang="ja">

<head>

    <meta charset="UTF-8">

    <title>変更完了</title>
	
	<link rel="stylesheet" href="style.css">
	
</head>

<body>

<%@ include file="header.jsp" %>
<div class="wrapper">
<%@ include file="sidebar.jsp" %>
	<div class="content">
		<h2>科目情報の変更が完了しました</h2>
				
		<br>
		
		<a href="SubjectListServlet">科目一覧へ戻る</a>
	</div>
</div>

<%@ include file="footer.jsp" %>

</body>

</html>
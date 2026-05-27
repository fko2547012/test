<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログアウト</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="wrapper">
		<%@ include file="sidebar.jsp" %>
		<div class="content">
			<h2>ログアウト</h2>
			<p>ログアウトしました</p>
			<a href="login.jsp">ログイン</a>
		</div>
	</div>
<%@ include file="footer.jsp" %>
</body>
</html>
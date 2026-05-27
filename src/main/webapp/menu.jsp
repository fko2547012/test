<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>メインメニュー</title>
	<link rel="stylesheet" href="style.css">
</head>

<body>
<%@ include file="header.jsp" %>
<div class="wrapper">
<%@ include file="sidebar.jsp" %>
	<div class="content">
		<h2>メニュー</h2>
		<div class="menu-container">
        <div class="menu-box-red">
            <a href="StudentListServlet">学生管理</a>
        </div>
        <div class="menu-box-gre">
            <a href="#">成績管理</a>
            <a href="test_regist.jsp">成績登録</a>
            <a href="test_list.jsp">成績参照</a>
        </div>
        <div class="menu-box-bul">
            <a href="subject_list.jsp">科目管理</a>
        </div>
    </div>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
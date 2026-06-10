<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>科目登録</title>
<link rel="stylesheet" href="style.css">
</head>

<body>

<%@ include file="header.jsp" %>

<div class="wrapper">

<%@ include file="sidebar.jsp" %>

<div class="content">

<h2>科目情報登録</h2>

<form action="SubjectCreateServlet" method="post">

    <div class="form-group">
        <label>科目コード</label>
        <input type="text"
               name="cd"
               maxlength="3"
               required>
    </div>

    <div class="form-group">
        <label>科目名</label>
        <input type="text"
               name="name"
               maxlength="20"
               required>
    </div>

    <button type="submit">
        登録
    </button>

</form>

<br>

<a href="SubjectListServlet">
戻る
</a>

</div>

</div>

<%@ include file="footer.jsp" %>

</body>
</html>
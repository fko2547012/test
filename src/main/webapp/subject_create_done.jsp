<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <title>科目登録完了</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

<%@ include file="header.jsp" %>

<div class="wrapper">

    <%@ include file="sidebar.jsp" %>

    <div class="content">

        <h2>科目登録完了</h2>

        <p>登録が完了しました</p>

        <p>
            <a href="subject_create.jsp">戻る</a>
        </p>
		
		<br>
		
        <p>
            <a href="SubjectListServlet">科目一覧</a>
        </p>

    </div>

</div>

<%@ include file="footer.jsp" %>

</body>
</html>
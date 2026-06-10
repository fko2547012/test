<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <title>科目削除</title>

    <link rel="stylesheet" href="style.css">
</head>

<body>

<%@ include file="header.jsp" %>

<div class="wrapper">

    <%@ include file="sidebar.jsp" %>

    <div class="content">

        <h2>科目削除</h2>

        <p>
            以下の科目を削除しますか？
        </p>

        <table border="1">

            <tr>
                <th>科目コード</th>
                <td>${subject.cd}</td>
            </tr>

            <tr>
                <th>科目名</th>
                <td>${subject.name}</td>
            </tr>

        </table>

        <br>

        <form action="SubjectDeleteServlet"
              method="post">

            <input
                type="hidden"
                name="cd"
                value="${subject.cd}">

            <button type="submit">
                削除する
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
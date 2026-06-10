<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <title>科目変更</title>

    <link rel="stylesheet" href="style.css">
</head>

<body>

<%@ include file="header.jsp" %>

<div class="wrapper">

    <%@ include file="sidebar.jsp" %>

    <div class="content">

        <h2>科目情報変更</h2>

        <c:if test="${not empty errorMsg}">
            <p style="color:red;">
                ${errorMsg}
            </p>
        </c:if>

        <form action="SubjectUpdateServlet" method="post">

            <table>

                <tr>
                    <th>科目コード</th>
                    <td>
                        ${subject.cd}

                        <input
                            type="hidden"
                            name="cd"
                            value="${subject.cd}">
                    </td>
                </tr>

                <tr>
                    <th>科目名</th>
                    <td>
                        <input
                            type="text"
                            name="name"
                            value="${subject.name}"
                            required>
                    </td>
                </tr>

            </table>

            <br>

            <button type="submit">
                更新
            </button>

        </form>

        <br>

        <a href="SubjectListServlet">
            一覧へ戻る
        </a>

    </div>

</div>

<%@ include file="footer.jsp" %>

</body>
</html>

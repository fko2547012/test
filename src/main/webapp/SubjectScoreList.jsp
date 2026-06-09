<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>科目別成績一覧</title>
</head>
<body>

<h2>科目別成績一覧</h2>

<form action="SubjectScoreList.action" method="get">
    <label>科目を選択：</label>
    <select name="code">
        <c:forEach var="sub" items="${subjectList}">
            <option value="${sub.code}"
                <c:if test="${sub.code == param.code}">selected</c:if>>
                ${sub.name}
            </option>
        </c:forEach>
    </select>
    <button type="submit">表示</button>
</form>

<c:if test="${not empty scoreList}">
    <h3>${subjectName} の成績一覧</h3>

    <table border="1" cellpadding="8">
        <tr>
            <th>学生番号</th>
            <th>氏名</th>
            <th>点数</th>
        </tr>

        <c:forEach var="sc" items="${scoreList}">
            <tr>
                <td>${sc.studentNo}</td>
                <td>${sc.studentName}</td>
                <td>${sc.score}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>


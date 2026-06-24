<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>学生別成績一覧</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="wrapper">

    <%@ include file="sidebar.jsp" %>
	<div class="content">
		<h2>学生別成績一覧</h2>
		
		<form action="StudentScoreList.action" method="get">
		    <label>学生を選択：</label>
		    <select name="no">
		        <c:forEach var="stu" items="${studentList}">
		            <option value="${stu.no}"
		                <c:if test="${stu.no == param.no}">selected</c:if>>
		                ${stu.no}：${stu.name}
		            </option>
		        </c:forEach>
		    </select>
		    <button type="submit">表示</button>
		</form>
		
		<c:if test="${not empty scoreList}">
		    <h3>${studentName} の成績一覧</h3>
		
		    <table border="1" cellpadding="8">
		        <tr>
		            <th>科目名</th>
		            <th>点数</th>
		        </tr>
		
		        <c:forEach var="sc" items="${scoreList}">
		            <tr>
		                <td>${sc.subjectName}</td>
		                <td>${sc.score}</td>
		            </tr>
		        </c:forEach>
		    </table>
		</c:if>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>


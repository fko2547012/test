<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ja">

<head>

    <meta charset="UTF-8">

    <title>科目一覧</title>

    <style>

        table { width: 100%; border-collapse: collapse; margin-top: 20px; }

        th, td { border: 1px solid #ccc; padding: 10px; }

        th { background-color: #f0f0f0; }

        .btn { padding: 5px 10px; background: #007bff; color: #fff; text-decoration: none; }

        .btn:hover { background: #0056b3; }

    </style>
	
	<link rel="stylesheet" href="style.css">

</head>

<body>

<%@ include file="header.jsp" %>
<div class="wrapper">
<%@ include file="sidebar.jsp" %>
	<div class="content">
		<h2>科目一覧</h2>
		
		 
		
		<table>
		
		    <tr>
		
		        <th>科目コード</th>
		
		        <th>科目名</th>
		
		        <th>単位数</th>
		
		        <th>操作</th>
		
		    </tr>
		
		 
		
		    <c:forEach var="sub" items="${subjectList}">
		
		        <tr>
		
		            <td>${sub.code}</td>
		
		            <td>${sub.name}</td>
		
		            <td>${sub.unit}</td>
		
		            <td>
		
		                <a class="btn" href="SubjectUpdate.action?code=${sub.code}">変更</a>
		
		            </td>
		
		        </tr>
		
		    </c:forEach>
		
		 
		
		</table>
	</div>
</div>

<%@ include file="footer.jsp" %>

</body>

</html>
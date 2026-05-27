<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		
		    <p style="color:red;">${errorMsg}</p>
		
		</c:if>
		
		 
		
		<form action="SubjectUpdate.action" method="post">
		
		 
		
		    <label>科目コード</label><br>
		
		    <input type="text" name="code" value="${subject.code}" readonly><br><br>
		
		 
		
		    <label>科目名</label><br>
		
		    <input type="text" name="name" value="${subject.name}" required><br><br>
		
		 
		
		    <label>単位数</label><br>
		
		    <input type="number" name="unit" value="${subject.unit}" min="1" required><br><br>
		
		 
		
		    <button type="submit">変更</button>
		
		</form>
		
		 
		
		<br>
		
		<a href="SubjectList.action">戻る</a>
	</div>
</div>

<%@ include file="footer.jsp" %>

</body>

</html>
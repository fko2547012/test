<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ja">

<head>

    <meta charset="UTF-8">

    <title>学生情報変更</title>

	<link rel="stylesheet" href="style.css">

</head>

<body>

<%@ include file="header.jsp" %>
<div class="wrapper">
<%@ include file="sidebar.jsp" %>
	<div class="content">
		<h2>学生情報変更</h2>
		
		 
		
		<c:if test="${not empty errorMsg}">
		
		    <p style="color:red;">${errorMsg}</p>
		
		</c:if>
		
		 
		
		<form action="StudentUpdate.action" method="post">
		
		 
		
		    <label>学生番号</label><br>
		
		    <input type="text" name="no" value="${student.no}" readonly><br><br>
		
		 
		
		    <label>氏名</label><br>
		
		    <input type="text" name="name" value="${student.name}" required><br><br>
		
		 
		
		    <label>クラス</label><br>
		
		    <input type="text" name="classNo" value="${student.classNo}"><br><br>
		
		 
		
		    <label>入学年度</label><br>
		
		    <input type="text" name="year" value="${student.year}" readonly><br><br>
		
		 
		
		    <label>
		
		        <input type="checkbox" name="active"
		
		               <c:if test="${student.active}">checked</c:if>>
		
		        在学中
		
		    </label><br><br>
		
		 
		
		    <button type="submit">変更</button>
		
		</form>
		
		 
		
		<br>
		
		<a href="StudentList.action">戻る</a>
	</div>
</div>
<%@ include file="footer.jsp" %>

</body>

</html>
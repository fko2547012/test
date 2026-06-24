<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html = lang="ja">
<head>
<meta charset="UTF-8">
<title>学生情報登録</title>
<link rel="stylesheet" href="style.css">
<style>
        body { font-family: sans-serif; }
        .container { margin: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], select { width: 100%; max-width: 400px; padding: 8px; box-sizing: border-box; }
        .btn-submit { background-color: #6c757d; color: white; border: none; padding: 10px 20px; cursor: pointer; border-radius: 4px; }
        .back-link { display: inline-block; margin-top: 10px; font-size: 0.9em; }
        h2 { background-color: #f8f9fa; padding: 10px; border-bottom: 1px solid #ddd; }
</style>
</head>
<body>
<%@ include file="header.jsp" %>
 
<div class="wrapper">
	<%@ include file="sidebar.jsp" %>
	<div class="container">
	<h2>学生情報登録</h2>
	
	<c:if test="${not empty error}">
    	<p style="color:red;">
    	    ${error}
    	</p>
	</c:if>
		
	<form action="StudentCreateServlet" method="post">
	
	<div class="form-group">
	<label for="ent_year">入学年度</label>
	<select name="ent_year"
	        id="ent_year"
	        required>
	
	    <option value="">
	        --------
	    </option>
	
	    <c:forEach var="year"
	               items="${ent_year_list}">
	
	    	<option value="${year}">
	            ${year}
	        </option>
	
	    </c:forEach>
	
	</select>
	</div>
	
	 
	
	        <div class="form-group">
	<label for="no">学生番号</label>
	<input type="text"
       name="no"
       id="no"
       maxlength="10"
       required>
	</div>
	
	 
	
	        <div class="form-group">
	<label for="name">氏名</label>
	<input type="text"
       name="name"
       id="name"
       maxlength="30"
       required>
	</div>
	
	 
	
	        <div class="form-group">
	<label for="class_num">クラス</label>
	<select name="class_num" id="class_num">
	<c:forEach var="class_item" items="${class_num_list}">
	<option value="${class_item}" <c:if test="${class_item == '101'}">selected</c:if>>${class_item}</option>
	</c:forEach>
	</select>
	</div>
	
	 
	
	        <div class="form-group">
	<button type="submit" name="end" class="btn-submit">登録して終了</button>
	</div>
	
	
	
	        <a href="StudentListServlet" class="back-link">戻る</a>
	
	 
	
	</form>
	</div>
</div>
 
<%@ include file="footer.jsp" %>
</body>
</html>
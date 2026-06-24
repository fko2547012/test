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
		
		<c:if test="${not empty error}">
		    <p style="color:red;">
		        ${error}
		    </p>
		</c:if>
		 
		<form action="StudentUpdateServlet" method="post">

		    <label>学生番号</label><br>
		    <input type="hidden"
		           name="no"
		           value="${student.no}">

			${student.no}
			<br>
		
		    <label>氏名</label><br>
		    <input type="text"
                   name="name"
                   value="${student.name}"
                   maxlength="30"
                   required>
					
			<br>
			
		    <label>クラス</label><br>
		    <select name="class_num">

			    <c:forEach var="class_item"
			               items="${class_num_list}">
			
			        <option value="${class_item}"
			            <c:if test="${student.classNum == class_item}">
			                selected
			            </c:if>>
			            ${class_item}
			        </option>
			
			    </c:forEach>
			
			</select>
			
			<br>
			
		    <label>入学年度</label><br>
		    <select name="ent_year" required>

			    <option value="">
			        --------
			    </option>
			
			    <c:forEach var="year"
			               items="${ent_year_list}">
			
			        <option value="${year}"
			            <c:if test="${student.entYear == year}">
			                selected
			            </c:if>>
			            ${year}
			        </option>
			
			    </c:forEach>
			
			</select>
			
			<br>
			
			<tr>
			    <th>在学中</th>
			    <td>
			        <input type="checkbox"
			               name="attend"
			               value="true"
			               ${student.attend ? 'checked="checked"' : ''}>
			    </td>
			</tr>
			
			<br>
			
		    <button type="submit">
		        変更
		    </button>
		
		</form>
		
		<br>
		
		<a href="StudentListServlet">
		    戻る
		</a>
		
	</div>
</div>
<%@ include file="footer.jsp" %>

</body>

</html>
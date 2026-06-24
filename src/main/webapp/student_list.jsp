<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<title>学生管理一覧</title>
<link rel="stylesheet" href="style.css">
    <style>

        body { font-family: sans-serif; margin: 20px; }

        table {

            width: 100%; border-collapse: collapse; margin-top: 20px;

        }

        th, td {

            border: 1px solid #ccc; padding: 10px; text-align: left;

        }

        th { background-color: #f0f0f0; }

        .btn {

            padding: 5px 10px; background-color: #007bff;

            color: #fff; text-decoration: none; border-radius: 4px;

        }

        .btn:hover { background-color: #0056b3; }

        .delete-btn {

            background-color: #dc3545;

        }

        .delete-btn:hover {

            background-color: #a71d2a;

        }

    </style>

</head>

<body>

<%@ include file="header.jsp" %>

<div class="wrapper">
<%@ include file="sidebar.jsp" %>
	<div class="content">
		<h2>学生管理一覧</h2>
		
		 
		
		<!-- メッセージ表示 -->
		
		<c:if test="${not empty message}">
		
		    <p style="color: green;">${message}</p>
		
		</c:if>
		
		 
		
		<table border="1">
		
		    <tr>
		
		        <th>学生番号</th>
		
		        <th>氏名</th>
		
		        <th>クラス</th>
		
		        <th>入学年度</th>
		
		        <th>在学中</th>
		
		        <th>操作</th>
		
		    </tr>
		
		 
		
		    <c:forEach var="s" items="${studentList}">
		
		        <tr>
		
		            <td>${s.no}</td>
		
		            <td>${s.name}</td>
		
		            <td>${s.classNum}</td>
		
		            <td>${s.entYear}</td>
		
		            <td>

					    <c:choose>
					
					        <c:when test="${s.attend}">
					            ○
					        </c:when>
					
					        <c:otherwise>
					            ×
					        </c:otherwise>
					
					    </c:choose>
					
					</td>
		
		            <td>
		
		                <a class="btn" href="StudentUpdateServlet?no=${s.no}">
        					変更
    					</a>
		
		            </td>
		
		        </tr>
		
		    </c:forEach>
		
		 
		
		</table>
	</div>
</div>
<%@ include file="footer.jsp" %>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="bean.Subject" %>
<%@ page import="bean.Student" %>
<%@ page import="bean.SubjectResult" %>
<%@ page import="bean.StudentResult" %>

<!DOCTYPE html>

<html lang="ja">

<head>
<meta charset="UTF-8">
<title>成績参照</title>
<link rel="stylesheet" href="style.css">
<style>
table {
    border-collapse: collapse;
    margin-top: 10px;
}

th, td {
    border: 1px solid #000;
    padding: 5px 10px;
}

.search-area {
    margin-bottom: 20px;
}
</style>

</head>

<body>
<%@ include file="header.jsp" %>
<div class="wrapper">

    <%@ include file="sidebar.jsp" %>
    <div class="content">
		<h2>成績参照</h2>
		
		<%
		Integer selectedYear =
		(Integer)request.getAttribute("f1");
		
		String selectedClass =
		(String)request.getAttribute("f2");
		
		String selectedSubject =
		(String)request.getAttribute("f3");
		
		String selectedStudentNo =
		(String)request.getAttribute("f4");
		%>
		
		<div class="search-area">
		<%
		String error =
		    (String)request.getAttribute("error");
		
		if(error != null){
		%>
		
		<p style="color:red;">
		    <%= error %>
		</p>
		
		<%
		}
		%>
		<form action="TestListServlet" method="post">
		
		<h3>科目情報</h3>
		
		入学年度
		
		<select name="f1">
		
		<option value="">------</option>
		
		<%
		List<Integer> entYearList =
		(List<Integer>)request.getAttribute("entYearList");
		
		if(entYearList != null){
		
		for(Integer year : entYearList){
		
		%>
		
		<option value="<%= year %>"
		<%= (selectedYear != null &&
		      selectedYear.equals(year))
		      ? "selected" : "" %>>
		
		<%= year %>
		
		</option>
		
		<%
		}
		}
		%>
		
		</select>
		
		クラス
		
		<select name="f2">
		
		<option value="">------</option>
		
		<%
		List<String> classNumList =
		(List<String>)request.getAttribute("classNumList");
		
		if(classNumList != null){
		
		for(String classNum : classNumList){
		
		%>
		
		<option value="<%= classNum %>"
		<%= classNum.equals(selectedClass)
		      ? "selected" : "" %>>
		
		<%= classNum %>
		
		</option>
		
		<%
		}
		}
		%>
		
		</select>
		
		科目
		
		<select name="f3">
		
		<option value="">------</option>
		
		<%
		List<Subject> subjectList =
		(List<Subject>)request.getAttribute("subjectList");
		
		if(subjectList != null){
		
		for(Subject subject : subjectList){
		
		%>
		
		<option value="<%= subject.getCd() %>"
		<%= subject.getCd().equals(selectedSubject)
		      ? "selected" : "" %>>
		
		<%= subject.getName() %>
		
		</option>
		
		<%
		}
		}
		%>
		
		</select>
		
		<input type="hidden" name="f" value="sj">
		
		<input type="submit" value="検索">
		
		</form>
		
		</div>
		
		<hr>
		
		<div class="search-area">
		
		<form action="TestListServlet" method="post">
		
		<h3>学生情報</h3>
		
		学生番号
		
		<input
		type="text"
		name="f4"
		value="<%= selectedStudentNo == null ? "" : selectedStudentNo %>">
		
		<input type="hidden" name="f" value="st">
		
		<input type="submit" value="検索">
		
		</form>
		
		</div>
		
		<%
		String subjectError =
		(String)request.getAttribute(
		"subjectError");
		
		if(subjectError != null){
		%>
		
		<p style="color:red;">
		<%= subjectError %>
		</p>
		
		<%
		}
		%>
		
		<%
		List<SubjectResult> subjectResults =
		(List<SubjectResult>)request.getAttribute(
		"subjectResults");
		
		if(subjectResults != null){
		%>
		
		<h3>科目検索結果</h3>
		
		<table>
		
		<tr>
		    <th>入学年度</th>
		    <th>クラス</th>
		    <th>学生番号</th>
		    <th>氏名</th>
		    <th>1回</th>
		    <th>2回</th>
		</tr>
		
		<%
		for(SubjectResult r : subjectResults){
		%>
		
		<tr>
		    <td><%= r.getEntYear() %></td>
		    <td><%= r.getClassNum() %></td>
		    <td><%= r.getStudentNo() %></td>
		    <td><%= r.getStudentName() %></td>
		    <td><%= r.getPoint1() %></td>
		    <td><%= r.getPoint2() %></td>
		</tr>
		
		<%
		}
		%>
		
		</table>
		
		<%
		}
		%>
		
		<%
		List<StudentResult> studentResults =
		(List<StudentResult>)request.getAttribute(
		"studentResults");
		
		Student student =
		(Student)request.getAttribute(
		"student");
		
		String studentError =
		(String)request.getAttribute(
		"studentError");

		if(studentError != null){
		%>

		<p style="color:red;">
		<%= studentError %>
		</p>

		<%
		}
		%>
		
		<%
		if(studentResults != null &&
		student != null){
		%>
		
		<h3>
		
		氏名：
		<%= student.getName() %>
		
		（
		<%= student.getNo() %>
		）
		
		</h3>
		
		<table>
		
		<tr>
		    <th>科目名</th>
		    <th>科目コード</th>
		    <th>回数</th>
		    <th>点数</th>
		</tr>
		
		<%
		for(StudentResult r : studentResults){
		%>
		
		<tr>
		    <td><%= r.getSubjectName() %></td>
		    <td><%= r.getSubjectCd() %></td>
		    <td><%= r.getTestNo() %></td>
		    <td><%= r.getPoint() %></td>
		</tr>
		
		<%
		}
		%>
		
		</table>
		
		<%
		}
		%>
		<p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
</div>
</body>
</html>

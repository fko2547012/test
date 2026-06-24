<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績削除</title>
    <link rel="stylesheet" href="style.css">
    <style>
        body { font-family: sans-serif; margin: 20px; }
        .search-box { background: #fff5f5; padding: 15px; border-radius: 5px; margin-bottom: 20px; border: 1px solid #fab1a0; }
        .search-box label { margin-right: 15px; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background: #ffeaa7; }
        .btn { padding: 6px 12px; cursor: pointer; }
        .btn-primary { background: #007bff; color: white; border: none; }
        .btn-danger { background: #d63031; color: white; border: none; font-weight: bold; }
        .error { color: red; }
        .warn-text { color: #d63031; font-weight: bold; }
    </style>
    <script>
        // すべてのチェックボックスを一括選択/解除するJavaScript
        function toggleAll(source) {
            checkboxes = document.getElementsByName('delete_student_no');
            for(var i=0, n=checkboxes.length; i<n; i++) {
                checkboxes[i].checked = source.checked;
            }
        }
    </script>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="wrapper">

    <%@ include file="sidebar.jsp" %>
    <div class="content">
	    <h2>成績削除</h2>
	    <p class="warn-text">※削除した成績データは元に戻せません。十分注意してください。</p>
	
	    <c:if test="${not empty error}">
	        <p class="error">${error}</p>
	    </c:if>
	    <c:if test="${not empty message}">
	        <p style="color: green; font-weight: bold;">${message}</p>
	    </c:if>
	
	    <div class="search-box">
	        <form action="TestDelete.action" method="post">
	            <label>入学年度:
	                <select name="ent_year" required>
	                    <option value="">-- 選択 --</option>
	                    <c:forEach var="year" items="${entYearList}">
	                        <option value="${year}" ${year == selectedYear ? 'selected' : ''}>${year}</option>
	                    </c:forEach>
	                </select>
	            </label>
	
	            <label>クラス:
	                <select name="class_num" required>
	                    <option value="">-- 選択 --</option>
	                    <c:forEach var="classNum" items="${classNumList}">
	                        <option value="${classNum}" ${classNum == selectedClass ? 'selected' : ''}>${classNum}</option>
	                    </c:forEach>
	                </select>
	            </label>
	
	            <label>科目:
	                <select name="subject_cd" required>
	                    <option value="">-- 選択 --</option>
	                    <c:forEach var="subject" items="${subjectList}">
	                        <option value="${subject.cd}" ${subject.cd == selectedSubject ? 'selected' : ''}>${subject.name}</option>
	                    </c:forEach>
	                </select>
	            </label>
	
	            <label>回数:
	                <select name="no" required>
	                    <option value="1" ${selectedNo == 1 ? 'selected' : ''}>1回目</option>
	                    <option value="2" ${selectedNo == 2 ? 'selected' : ''}>2回目</option>
	                </select>
	            </label>
	
	            <button type="submit" class="btn btn-primary">成績データを検索</button>
	        </form>
	    </div>
	
	    <c:if test="${not empty testList}">
	        <form action="TestDeleteExecute.action" method="post">
	            <input type="hidden" name="ent_year" value="${selectedYear}">
	            <input type="hidden" name="class_num" value="${selectedClass}">
	            <input type="hidden" name="subject_cd" value="${selectedSubject}">
	            <input type="hidden" name="no" value="${selectedNo}">
	
	            <table>
	                <thead>
	                    <tr>
	                        <th style="width: 40px;"><input type="checkbox" onClick="toggleAll(this)"></th>
	                        <th>学生番号</th>
	                        <th>氏名</th>
	                        <th>クラス</th>
	                        <th>現在の点数</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <c:forEach var="test" items="${testList}">
	                        <tr>
	                            <td>
	                                <input type="checkbox" name="delete_student_no" value="${test.student.no}">
	                            </td>
	                            <td>${test.student.no}</td>
	                            <td>${test.student.name}</td>
	                            <td>${test.classNum}</td>
	                            <td><strong>${test.point} 点</strong></td>
	                        </tr>
	                    </c:forEach>
	                </tbody>
	            </table>
	
	            <div style="margin-top: 20px; text-align: right;">
	                <button type="submit" class="btn btn-danger" onclick="return confirm('チェックした学生の成績データを本当に削除しますか？');">選択した成績を削除する</button>
	            </div>
	        </form>
	    </c:if>
	
	    <c:if test="${empty testList && isSearched}">
	        <p>該当する成績データが見つかりませんでした。</p>
	    </c:if>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>

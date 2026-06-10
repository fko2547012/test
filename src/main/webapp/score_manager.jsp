<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>得点管理システム - 成績管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>

    <main class="score-container">
        <h3>成績管理</h3>
        
        <div class="search-condition-box">
            <div class="condition-row">
                <div class="cond-group">
                    <label>入学年度</label>
                    <select name="entYear" disabled><option>${f1}</option></select>
                </div>
                <div class="cond-group">
                    <label>クラス</label>
                    <select name="classNum" disabled><option>${f2}</option></select>
                </div>
                <div class="cond-group">
                    <label>科目</label>
                    <select name="subject" disabled><option>${f3}</option></select>
                </div>
                <div class="cond-group">
                    <label>回数</label>
                    <select name="count" disabled><option>${f4}</option></select>
                </div>
                <button type="button" class="btn-search" disabled>検索</button>
            </div>
        </div>

        <div class="subject-info">
            科目：${f3}（${f4}回）
        </div>

        <form action="ScoreRegist.action" method="post">
            
            <table class="score-table">
                <thead>
                    <tr>
                        <th>No.3 入学年度</th>
                        <th>No.4 クラス</th>
                        <th>No.5 学生番号</th>
                        <th>No.6 氏名</th>
                        <th>No.7 点数</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    // サーブレット側から渡された成績リストを取得
                    List<Object> tests = (List<Object>) request.getAttribute("tests");
                    if (tests != null && !tests.isEmpty()) {
                        for (Object test : tests) {
                %>
                    <tr>
                        <td>${test.student.entYear}</td>
                        
                        <td>${test.classNum}</td>
                        
                        <td>${test.student.no}</td>
                        
                        <td>${test.student.name}</td>
                        
                        <td>
                            <input type="number" 
                                   name="point_${test.student.no}" 
                                   value="${test.point}" 
                                   min="0" max="100" 
                                   placeholder="入力" 
                                   class="input-point">
                        </td>
                    </tr>
                    
                    <input type="hidden" name="regist" value="${test.student.no}">
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5" style="text-align: center; padding: 20px; color: #888;">
                            対象の学生データが存在しません。
                        </td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>

            <input type="hidden" name="count" value="${f4}">
            
            <input type="hidden" name="subject" value="${f3}">

            <div class="submit-box">
                <input type="submit" value="登録して終了" class="btn-submit">
            </div>
            
        </form>
    </main>

</body>
</html>

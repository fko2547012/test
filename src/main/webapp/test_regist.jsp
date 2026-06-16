<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/libs/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績登録</title>
    <style>
        body { font-family: sans-serif; margin: 20px; }
        .search-box { background: #f5f5f5; padding: 15px; border-radius: 5px; margin-bottom: 20px; }
        .search-box label { margin-right: 15px; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background: #e0e0e0; }
        .btn { padding: 6px 12px; cursor: pointer; }
        .btn-primary { background: #007bff; color: white; border: none; }
        .btn-success { background: #28a745; color: white; border: none; }
        .error { color: red; }
    </style>
</head>
<body>

    <h2>成績登録</h2>

    <!-- エラー表示用 -->
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <!-- 1. 検索条件入力エリア -->
    <div class="search-box">
        <form action="TestRegist.action" method="post">
            <label>入学年度:
                <select name="ent_year">
                    <option value="">-- 選択してください --</option>
                    <c:forEach var="year" items="${entYearList}">
                        <option value="${year}" ${year == selectedYear ? 'selected' : ''}>${year}</option>
                    </c:forEach>
                </select>
            </label>

            <label>クラス:
                <select name="class_num">
                    <option value="">-- 選択してください --</option>
                    <c:forEach var="classNum" items="${classNumList}">
                        <option value="${classNum}" ${classNum == selectedClass ? 'selected' : ''}>${classNum}</option>
                    </c:forEach>
                </select>
            </label>

            <label>科目:
                <select name="subject_cd">
                    <option value="">-- 選択してください --</option>
                    <c:forEach var="subject" items="${subjectList}">
                        <option value="${subject.cd}" ${subject.cd == selectedSubject ? 'selected' : ''}>${subject.name}</option>
                    </c:forEach>
                </select>
            </label>

            <label>回数:
                <select name="no">
                    <option value="1" ${selectedNo == 1 ? 'selected' : ''}>1回目</option>
                    <option value="2" ${selectedNo == 2 ? 'selected' : ''}>2回目</option>
                </select>
            </label>

            <button type="submit" class="btn btn-primary" name="search">検索</button>
        </form>
    </div>

    <!-- 2. 成績入力・一覧エリア -->
    <c:if test="${not empty testList}">
        <form action="TestRegistExecute.action" method="post">

            <input type="hidden" name="ent_year" value="${selectedYear}">
            <input type="hidden" name="class_num" value="${selectedClass}">
            <input type="hidden" name="subject_cd" value="${selectedSubject}">
            <input type="hidden" name="no" value="${selectedNo}">

            <table>
                <thead>
                    <tr>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>クラス</th>
                        <th>点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="test" items="${testList}" varStatus="status">
                        <tr>
                            <td>
                                ${test.student.no}
                                <!-- 各行の学生番号を配列として送信 -->
                                <input type="hidden" name="student_no" value="${test.student.no}">
                            </td>
                            <td>${test.student.name}</td>
                            <td>${test.classNum}</td>
                            <td>
                                <!-- 点数入力欄。未登録なら空、登録済ならその点数を初期値にする -->
                                <input type="number" name="point" value="${test.point >= 0 ? test.point : ''}" min="0" max="100" style="width: 60px;">
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div style="margin-top: 20px; text-align: right;">
                <button type="submit" class="btn btn-success">登録して保存</button>
            </div>
        </form>
    </c:if>

    <c:if test="${empty testList && isSearched}">
        <p>該当する学生情報が見つかりませんでした。条件を確認してください。</p>
    </c:if>

</body>
</html>

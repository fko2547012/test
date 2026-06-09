<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/libs/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績変更</title>
    <style>
        body { font-family: sans-serif; margin: 20px; }
        .search-box { background: #eef7ff; padding: 15px; border-radius: 5px; margin-bottom: 20px; border: 1px solid #bce0ff; }
        .search-box label { margin-right: 15px; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background: #d0e8ff; }
        .btn { padding: 6px 12px; cursor: pointer; }
        .btn-primary { background: #007bff; color: white; border: none; }
        .btn-warning { background: #ffc107; color: #212529; border: none; font-weight: bold; }
        .error { color: red; }
        .old-point { color: #6c757d; font-size: 0.9em; margin-left: 10px; }
    </style>
</head>
<body>

    <h2>成績変更</h2>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>

    <div class="search-box">
        <form action="TestUpdate.action" method="post">
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

            <button type="submit" class="btn btn-primary" name="search">成績データを検索</button>
        </form>
    </div>

    <c:if test="${not empty testList}">
        <form action="TestUpdateExecute.action" method="post">
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
                        <th>現在の点数 → 変更後の点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="test" items="${testList}" varStatus="status">
                        <tr>
                            <td>
                                ${test.student.no}
                                <input type="hidden" name="student_no" value="${test.student.no}">
                            </td>
                            <td>${test.student.name}</td>
                            <td>${test.classNum}</td>
                            <td>
                                <span class="old-point">変更前: ${test.point} 点</span> 
                                &nbsp;&nbsp;→&nbsp;&nbsp;
                                <input type="number" name="point" value="${test.point}" min="0" max="100" style="width: 60px;" required>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div style="margin-top: 20px; text-align: right;">
                <button type="submit" class="btn btn-warning" onclick="return confirm('成績を変更します。よろしいですか？');">変更を確定する</button>
            </div>
        </form>
    </c:if>

    <c:if test="${empty testList && isSearched}">
        <p>指定された条件の成績データが見つかりませんでした。まだ成績が登録されていないか、条件が間違っています。</p>
    </c:if>

</body>
</html>

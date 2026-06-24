<%@ page contentType="text/html; charset=UTF-8" %>
<c:if test="${not empty user}">
<div class="sidebar">
    <nav>
        <ul>
            <li><a href="menu.jsp">メニュー</a></li>

            <li><a href="StudentListServlet">学生管理</a></li>
			<ul>
				<li><a href="StudentCreateServlet">学生登録</a></li>
			</ul>
            <li>成績管理</li>
            <ul>
                <li><a href="TestRegist.action">成績登録</a></li>
                <li><a href="TestUpdate.action">成績変更</a></li>
                <li><a href="TestDelete.action">成績削除</a></li>
                <li><a href="TestListServlet">成績参照</a></li>
            </ul>

            <li><a href="SubjectListServlet">科目管理</a></li>
			<ul>
				<li><a href="SubjectCreateServlet">科目登録</a></li>
			</ul>
			
        </ul>
    </nav>
</div>
</c:if>
<%@ page contentType="text/html; charset=UTF-8" %>

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
                <li><a href="#">成績登録</a></li>
                <li><a href="#">成績参照</a></li>
            </ul>

            <li><a href="SubjectListServlet">科目管理</a></li>
			<ul>
				<li><a href="SubjectCreateServlet">科目登録</a></li>
			</ul>
			
        </ul>
    </nav>
</div>

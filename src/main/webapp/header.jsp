<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header">

    <div style="background-color:#007BFF; color:white; padding:5px; display: flex;">
        得点管理システム
    </div>

    <div class="header-user">

        <c:if test="${not empty user}">
            <span>
                ${user.name} 様
            </span>

            <a href="Logout.action">
                ログアウト
            </a>
        </c:if>

    </div>

</header>
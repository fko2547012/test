<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="wrapper">
		<%@ include file="sidebar.jsp" %>
		<div class="content">
		    <h2>ログイン</h2>
		    <form action="LoginExecute.action" method="post">
		        <div class="form-group">
		            <label>ユーザーID</label>
		            <input type="text" name="id" required>
		        </div>
		        <div class="form-group">
		            <label>パスワード</label>
		            <input type="password" id="password" name="password" required>
		        </div>
		        <div class="form-group2">
				    <label>
				        <input type="checkbox" onclick="togglePassword()">
				        パスワードを表示する
				   	</label>
				</div>
	        <div class="cent">
	        <button type="submit" class="login-btn">ログイン</button>
	    	</div>
	    	</form>
	    </div>
</div>
<%@ include file="footer.jsp" %>
<script>
function togglePassword() {
    const pwd = document.getElementById("password");
    if (pwd.type === "password") {
        pwd.type = "text";
    } else {
        pwd.type = "password";
    }
}
</script>
</body>
</html>
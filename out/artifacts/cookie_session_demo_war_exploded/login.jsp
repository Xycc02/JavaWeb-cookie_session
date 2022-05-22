<%--
  Created by IntelliJ IDEA.
  User: 徐宇超
  Date: 2022/4/10
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
${cookie.username}
<form action="/cookieServlet?action=loginServlet" method="post">
<%--    <input type="hidden" name="action" value="loginServlet">--%>
    用户名:<input type="text" name="username" value="${cookie.username.value}"><br>
    密码:<input type="password" name="password" value="${cookie.password.value}"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>

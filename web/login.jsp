<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/2/17
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login form</title>
</head>
<body>
<form method="post" action="login">
    Email:<input type="text" name="email"/><br/>
    Password:<input type="password" name="pass"/><br/>
    <input type="submit" value="login"/>
</form>
<a href="createAcc.jsp">Create an account</a>
</body>
</html>
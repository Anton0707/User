<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/2/17
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Data</title>
</head>
<style>
    div.ex {
        text-align: right width:300px;
        padding: 10px;
        border: 5px solid grey;
        margin: 0px
    }
</style>
<body>
<h1>User Data</h1>
<div class="ex">
    <form action="create" method="post">
        <table style="with: 50%">
            <tr>
                <td>lastName</td>
                <td><input type="text" name="lastName" /></td>
            </tr>
            <tr>
                <td>firstName</td>
                <td><input type="text" name="firstName" /></td>
            </tr>
            <tr>
                <td>password</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>login</td>
                <td><input type="text" name="login" /></td>
            </tr>
            <tr>
                <td>email</td>
                <td><input type="text" name="email" /></td>
            </tr>
            <tr>
                <td>dOb</td>
                <td><input type="date" name="dOb" /></td>
            </tr>
        </table>
        <input type="submit" value="register" />
    </form>
</div>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/2/17
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>welcome</title>
</head>
<body>
<%
    if(session.getAttribute("email") == null){
        response.sendRedirect("login.jsp");
    }
%>
    Welcome ${email}

<h1>Online Shop</h1>
<br>
<h3>List of items</h3>
<table border="1" cellpadding="5">
    <tr>
        <th>BRAND</th>
        <th>MODEL</th>
        <th>PRICE</th>
    </tr>
    <c:forEach var="item" items="${list}">
        <tr>
            <td><c:out value="${item.brand}"/></td>
            <td><c:out value="${item.model}"/></td>
            <td><c:out value="${item.price}"/></td>

        </tr>
    </c:forEach>
</table>

<form action="logout">
    <input type="submit" value="logout">
</form>
</body>
</html>

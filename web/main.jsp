<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main</title>
</head>

<body>
<h1>Online Shop</h1>
        <a href="login.jsp">SIGN IN</a>
<h3>List of items</h3>
<table border="1" cellpadding="5">
    <tr>
        <th>BRAND</th>
        <th>MODEL</th>
        <th>ITEM</th>
    </tr>
    <c:forEach var="item" items="${list}">
        <tr>
            <td><c:out value="${item.brand}"/></td>
            <td><c:out value="${item.model}"/></td>
            <td><c:out value="${item.price}"/></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
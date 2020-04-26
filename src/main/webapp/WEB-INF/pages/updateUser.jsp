<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: saith
  Date: 06.04.2020
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>$Title$</title>
</head>
<body>

<table>
    <tr>
        <th>id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
    </tr>

    <tr>
        <form action="/updateUser" method="post">
            <td>${user.id}</td>
            <input required type="hidden" name="id" value="${user.id}">
            <td><input required type="text" name="firstName" placeholder="new name"></td>
            <td><input required type="text" name="lastName" placeholder="new last name"></td>
            <td><input required type="text" name="email" placeholder="new email"></td>
            <td><input type="submit" value="Update user"></td>
        </form>
    </tr>

</table>
</body>
</html>

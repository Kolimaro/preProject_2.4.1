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
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
    </tr>

    <tr>
        <form action="/addUser" method="post">
            <td><input required type="text" name="firstName" placeholder="name"></td>
            <td><input required type="text" name="lastName" placeholder="last name"></td>
            <td><input required type="text" name="email" placeholder="email"></td>
            <td>
                <input type="submit" value="Add user">
            </td>
        </form>
    </tr>

</table>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div>
    <table>
        <thead>
        <th>ID</th>
        <th>UserName</th>
        <th>Password</th>
        <th>Roles</th>
        </thead>
        <form:form method="POST" modelAttribute="userForm">
            <tr>
                <td>${userForm.id}</td>
                <td>
                    <div>
                        <form:input type="text" path="username" placeholder="Username"
                                    autofocus="true"></form:input>
                        <form:errors path="username"></form:errors>
                            ${usernameError}
                    </div>
                </td>
                <td>
                    <div>
                        <form:input type="password" path="password" placeholder="Password"></form:input>
                    </div>
                </td>
                <td>
                    <div>
                        <input type="text" name="userRole" value="userRole" placeholder="Role"/>
                    </div>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/update" method="post">
                        <input type="hidden" name="userId" value="${userForm.id}"/>
                        <input type="hidden" name="action" value="update"/>
                        <button type="submit">Update</button>
                    </form>
                </td>
            </tr>
        </form:form>
    </table>
    <a href="/">Главная</a>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">Выйти</a></h4>
    </sec:authorize>
</div>
</body>
</html>
<%@ page import="ru.avenue.dev.servlets.entities.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: super
  Date: 16.05.2022
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form method="post" action="/users">
    <h1>${cookie.get("username").value}</h1>
    <label>
        <input type="text" name="user_name">
    </label>
    <input type="submit" value="submit">
</form>
<table>
    <th>ID</th>
    <th>CREATE TIME</th>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        for (User user : users) {
    %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getCreateTime()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>

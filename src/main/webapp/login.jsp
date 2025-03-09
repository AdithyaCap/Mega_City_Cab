<%--
  Created by IntelliJ IDEA.
  User: poorn
  Date: 2/13/2025
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = request.getParameter("message");
    if (message != null) {
%>
<p style="color: green;"><%= message %></p>
<% } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login.css">
    <title>Login - Mega City Cab</title>
</head>
<body>
<h1>Login to Mega City Cab</h1>

<form action="login" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <button type="submit">Login</button>
    </div>
</form>

<!-- Add a Register button linking to register.jsp -->
<div style="margin-top: 10px;">
    <p>Don't have an account? <a href="register.jsp">Register here</a></p>
</div>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

</body>
</html>

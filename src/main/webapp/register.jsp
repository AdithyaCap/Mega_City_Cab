<%-- Created by IntelliJ IDEA. User: poorn Date: 2/14/2025 Time: 4:00 PM --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Registration</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
<h2>Register</h2>
<form action="register" method="post">
    <div>
        <label>Name:</label>
        <input type="text" name="username" required>
    </div>
    <div>
        <label>Address:</label>
        <input type="text" name="address" required>
    </div>
    <div>
        <label>NIC:</label>
        <input type="text" name="nic" required>
    </div>
    <div>
        <label>Phone Number:</label>
        <input type="text" name="phone_number" required>
    </div>
    <div>
        <label>Email:</label>
        <input type="email" name="email" required>
    </div>
    <div>
        <label>Password:</label>
        <input type="password" name="password" required>
    </div>
    <div>
        <label>Role:</label>
        <select name="role" required>
            <option value="User">User</option>
            <option value="Driver">Driver</option>
        </select>
    </div>
    <button type="submit">Register</button>
</form>
</body>
</html>

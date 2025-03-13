<%--
  Created by IntelliJ IDEA.
  User: poorn
  Date: 2/13/2025
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Book a Ride - Mega City Cab</title>
</head>
<body>
<h1>Book a New Ride</h1>

<form action="BookRideController" method="post">
  <label for="pickupLocation">Pickup Location:</label>
  <input type="text" id="pickupLocation" name="pickupLocation" required><br><br>

  <label for="destination">Destination:</label>
  <input type="text" id="destination" name="destination" required><br><br>

  <label for="pickupTime">Pickup Time:</label>
  <input type="datetime-local" id="pickupTime" name="pickupTime" required><br><br>

  <button type="submit">Book Ride</button>
</form>
</body>
</html>

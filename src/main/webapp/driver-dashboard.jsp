<%--
  Created by IntelliJ IDEA.
  User: poorn
  Date: 3/5/2025
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page import="com.example.mega_city_cab.models.Driver" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.mega_city_cab.models.User" %>
<%@ page import="com.example.mega_city_cab.models.Booking" %>
<%@ page import="java.util.List" %>

<%
    // Retrieve the logged-in user and their assigned bookings
    User loggedUser = (User) session.getAttribute("user");
    List<Booking> assignedBookings = (List<Booking>) request.getAttribute("assignedBookings");
    List<Booking> pendingBookings = (List<Booking>) request.getAttribute("pendingBookings");
    List<Booking> inProgressBookings = (List<Booking>) request.getAttribute("inProgressBookings"); // Fix: Fetch "In Progress" bookings
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Driver Dashboard - Mega City Cab</title>
    <link rel="stylesheet" href="css/driver-dashboard.css">
</head>
<body>

<div class="dashboard-container">
    <h1>Welcome, <%= loggedUser.getUsername() %>!</h1>

    <div class="profile-info">
        <h3>Your Profile Details</h3>
        <p><strong>Username:</strong> <%= loggedUser.getUsername() %></p>
        <p><strong>Email:</strong> <%= loggedUser.getEmail() %></p>
        <p><strong>Address:</strong> <%= loggedUser.getAddress() %></p>
    </div>

    <div class="dashboard-links">
        <a href="logout.jsp" class="logout-link">Logout</a>
    </div>


    <!-- Pending Bookings Section -->
    <div class="bookings-section">
        <h2>Pending Bookings</h2>

        <% if (pendingBookings != null && !pendingBookings.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>Booking ID</th>
                <th>Pickup Location</th>
                <th>Drop-off Location</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <% for (Booking booking : pendingBookings) { %>
            <tr>
                <td><%= booking.getId() %></td>
                <td><%= booking.getPickupLocation() %></td>
                <td><%= booking.getDropLocation() %></td>
                <td><%= booking.getStatus() %></td>
                <td>
                    <form action="driver-dashboard" method="post">
                        <input type="hidden" name="action" value="updateBookingStatus">
                        <input type="hidden" name="bookingId" value="<%= booking.getId() %>">
                        <select name="status">
                            <option value="Pending" <%= "Pending".equals(booking.getStatus()) ? "selected" : "" %>>Pending</option>
                            <option value="In Progress">In Progress</option>
                        </select>
                        <button type="submit">Update</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>No pending bookings.</p>
        <% } %>
    </div>

    <!-- Fixing the In Progress Bookings Section -->
    <div class="bookings-section">
        <h2>In Progress Bookings</h2>

        <% if (inProgressBookings != null && !inProgressBookings.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>Booking ID</th>
                <th>Pickup Location</th>
                <th>Drop-off Location</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <% for (Booking booking : inProgressBookings) { %>
            <tr>
                <td><%= booking.getId() %></td>
                <td><%= booking.getPickupLocation() %></td>
                <td><%= booking.getDropLocation() %></td>
                <td><%= booking.getStatus() %></td>
                <td>
                    <form action="driver-dashboard" method="post">
                        <input type="hidden" name="action" value="updateBookingStatus">
                        <input type="hidden" name="bookingId" value="<%= booking.getId() %>">
                        <select name="status">
                            <option value="In Progress" <%= "In Progress".equals(booking.getStatus()) ? "selected" : "" %>>In Progress</option>
                            <option value="Completed">Completed</option>
                        </select>
                        <button type="submit">Update</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>No bookings in progress.</p>
        <% } %>
    </div>

</div>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.models.Booking" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.example.mega_city_cab.models.User" %>
<%@ page import="com.example.mega_city_cab.models.Car" %>
<%@ page import="java.sql.Driver" %>

<%
    HttpSession userSession = request.getSession(false);
    User username = (User) userSession.getAttribute("username");

    List<Booking> bookingList = (List<Booking>) request.getAttribute("bookings");
    List<User> userList = (List<User>) request.getAttribute("customers");
    List<Car> carList = (List<Car>) request.getAttribute("cars");
    List<User> driverList = (List<User>) request.getAttribute("drivers");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/admin-dashboard.css">
    <title>Admin Dashboard - Mega City Cab</title>
    <script>
        function showSection(sectionId) {
            document.querySelectorAll('.data-section').forEach(function(section) {
                section.style.display = 'none';
            });
            document.getElementById(sectionId).style.display = 'block';
        }

        function deleteItem(url, id) {
            if (confirm("Are you sure you want to delete this item?")) {
                window.location.href = url + "?id=" + id;
            }
        }
    </script>
</head>
<body>

<h1>Welcome to Admin Dashboard, ${user.username}</h1>

<div class="button-container">
    <button onclick="showSection('bookingsSection')">Bookings</button>
    <button onclick="showSection('customersSection')">Customers</button>
    <button onclick="showSection('carsSection')">Cars</button>
    <button onclick="showSection('driversSection')">Drivers</button>

</div>

<!-- Bookings Section -->
<div id="bookingsSection" class="data-section">
    <h2>Bookings</h2>
    <table>
        <thead>
        <tr>
            <th>Booking ID</th>
            <th>Pick up location</th>
            <th>Destination</th>
            <th>Car type</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% if (bookingList != null && !bookingList.isEmpty()) {
            for (Booking booking : bookingList) { %>
        <tr>
            <td><%= booking.getId() %></td>
            <td><%= booking.getPickupLocation() %></td>
            <td><%= booking.getDropLocation() %></td>
            <td><%= booking.getCarId() %></td>
            <td><%= booking.getFare() %></td>
            <td><%= booking.getStatus() %></td>
            <td><button onclick="deleteItem('deleteBooking', <%= booking.getId() %>)">Delete</button></td>
        </tr>
        <% }
        } else { %>
        <tr>
            <td colspan="7">No bookings found.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<!-- Customers Section -->
<div id="customersSection" class="data-section">
    <h2>Customers</h2>
    <table>
        <thead>
        <tr>
            <th>Customer ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% if (userList != null && !userList.isEmpty()) {
            for (User user : userList) { %>
        <tr>
            <td><%= user.getId()%></td>
            <td><%= user.getUsername()%></td>
            <td><%= user.getEmail()%></td>
            <td><%= user.getPhoneNumber()%></td>
            <td>
                <button onclick="deleteItem('deleteUser', <%= user.getId() %>)">Delete</button>
            </td>
        </tr>
        <% }
        } else { %>
        <tr>
            <td colspan="5">No users found.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<!-- Cars Section -->
<div id="carsSection" class="data-section">
    <h2>Cars</h2>
    <table>
        <thead>
        <tr>
            <th>Car ID</th>
            <th>Model</th>
            <th>Car Number</th>
            <th>Availability</th>
<%--            <th>Action</th>--%>
        </tr>
        </thead>
        <tbody>
        <% if (carList != null && !carList.isEmpty()) {
            for (Car car : carList) { %>
        <tr>
            <td><%= car.getCarId()%></td>
            <td><%= car.getCarModel()%></td>
            <td><%= car.getCarNumber()%></td>
            <td><%= car.getStatus()%></td>
<%--            <td><button onclick="deleteItem('deleteCar.jsp', <%= car.getCarId() %>)">Delete</button></td>--%>
        </tr>
        <% }
        } else { %>
        <tr>
            <td colspan="5">No cars found.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<!-- Drivers Section -->
<div id="driversSection" class="data-section">
    <h2>Drivers</h2>
    <table>
        <thead>
        <tr>
            <th>Driver ID</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% if (driverList != null && !driverList.isEmpty()) {
            for (User driver : driverList) { %>
            <tr>
                <td><%= driver.getId()%></td>
                <td><%= driver.getUsername()%></td>
                <td><%= driver.getPhoneNumber()%></td>

                <td><button onclick="deleteItem('deleteDriver', <%= driver.getId() %>)">Delete</button></td>
            </tr>
        <% }
        } else { %>
        <tr>
            <td colspan="5">No drivers found.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<p><a href="logout.jsp" class="logout-link">Logout</a></p>

</body>
</html>

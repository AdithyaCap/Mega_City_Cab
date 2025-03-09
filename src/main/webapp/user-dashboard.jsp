<%--
  Created by IntelliJ IDEA.
  User: poorn
  Date: 2/13/2025
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mega_city_cab.models.Booking" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.example.mega_city_cab.models.User" %>

<%
  // Simulate user session (in real case, pull this from actual logged-in user)
  HttpSession userSession = request.getSession(false);
  User username = (User) userSession.getAttribute("username");

  List<Booking> bookingList = (List<Booking>) request.getAttribute("bookings");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>User Dashboard - Mega City Cab</title>
  <link rel="stylesheet" href="css/user-dashboard.css">
</head>
<body>
<div class="container">
  <header>
    <h1>Welcome, <%= username %>!</h1>
    <p><a href="logout.jsp" class="logout-link">Logout</a></p>
  </header>

  <section class="booking-form">
    <h2>Book a Ride</h2>
    <form action="BookRideController" method="post">
      <label>Pickup Location:</label>
      <select id="pickupLocation" name="pickupLocation" required onchange="calculateFare()">
        <option value="">Select Pickup Location</option>
        <optgroup label="Western Province">
          <option value="Colombo">Colombo</option>
          <option value="Gampaha">Gampaha</option>
          <option value="Kalutara">Kalutara</option>
        </optgroup>
        <optgroup label="Central Province">
          <option value="Kandy">Kandy</option>
          <option value="Matale">Matale</option>
          <option value="Nuwara Eliya">Nuwara Eliya</option>
        </optgroup>
        <optgroup label="Southern Province">
          <option value="Galle">Galle</option>
          <option value="Matara">Matara</option>
          <option value="Hambantota">Hambantota</option>
        </optgroup>
        <optgroup label="Northern Province">
          <option value="Jaffna">Jaffna</option>
          <option value="Kilinochchi">Kilinochchi</option>
          <option value="Mannar">Mannar</option>
          <option value="Mullaitivu">Mullaitivu</option>
          <option value="Vavuniya">Vavuniya</option>
        </optgroup>
        <optgroup label="Eastern Province">
          <option value="Trincomalee">Trincomalee</option>
          <option value="Batticaloa">Batticaloa</option>
          <option value="Ampara">Ampara</option>
        </optgroup>
        <optgroup label="North Western Province">
          <option value="Puttalam">Puttalam</option>
          <option value="Kurunegala">Kurunegala</option>
        </optgroup>
        <optgroup label="North Central Province">
          <option value="Anuradhapura">Anuradhapura</option>
          <option value="Polonnaruwa">Polonnaruwa</option>
        </optgroup>
        <optgroup label="Uva Province">
          <option value="Badulla">Badulla</option>
          <option value="Monaragala">Monaragala</option>
        </optgroup>
        <optgroup label="Sabaragamuwa Province">
          <option value="Ratnapura">Ratnapura</option>
          <option value="Kegalle">Kegalle</option>
        </optgroup>
      </select>

      <label>Destination:</label>
      <select id="destination" name="destination" required onchange="calculateFare()">
        <option value="">Select Destination</option>
        <optgroup label="Western Province">
          <option value="Colombo">Colombo</option>
          <option value="Gampaha">Gampaha</option>
          <option value="Kalutara">Kalutara</option>
        </optgroup>
        <optgroup label="Central Province">
          <option value="Kandy">Kandy</option>
          <option value="Matale">Matale</option>
          <option value="Nuwara Eliya">Nuwara Eliya</option>
        </optgroup>
        <optgroup label="Southern Province">
          <option value="Galle">Galle</option>
          <option value="Matara">Matara</option>
          <option value="Hambantota">Hambantota</option>
        </optgroup>
        <optgroup label="Northern Province">
          <option value="Jaffna">Jaffna</option>
          <option value="Kilinochchi">Kilinochchi</option>
          <option value="Mannar">Mannar</option>
          <option value="Mullaitivu">Mullaitivu</option>
          <option value="Vavuniya">Vavuniya</option>
        </optgroup>
        <optgroup label="Eastern Province">
          <option value="Trincomalee">Trincomalee</option>
          <option value="Batticaloa">Batticaloa</option>
          <option value="Ampara">Ampara</option>
        </optgroup>
        <optgroup label="North Western Province">
          <option value="Puttalam">Puttalam</option>
          <option value="Kurunegala">Kurunegala</option>
        </optgroup>
        <optgroup label="North Central Province">
          <option value="Anuradhapura">Anuradhapura</option>
          <option value="Polonnaruwa">Polonnaruwa</option>
        </optgroup>
        <optgroup label="Uva Province">
          <option value="Badulla">Badulla</option>
          <option value="Monaragala">Monaragala</option>
        </optgroup>
        <optgroup label="Sabaragamuwa Province">
          <option value="Ratnapura">Ratnapura</option>
          <option value="Kegalle">Kegalle</option>
        </optgroup>
      </select>

      <label>Preferred Car Type:</label>
      <select name="carType">
        <option value="Economy">Economy</option>
        <option value="Standard">Standard</option>
        <option value="Luxury">Luxury</option>
      </select>

      <label>Fare:</label>
      <span><label>LKR:</label> <input type="text" id="fareAmount" name="fareAmount" readonly></span>


      <button type="submit">Book Now</button>
    </form>
  </section>

  <section class="booking-history">
    <h2>Booking History</h2>
    <table>
      <thead>
      <tr>
        <th>Booking No</th>
        <th>Pickup Location</th>
        <th>Destination</th>
        <th>Car Type</th>
        <th>Amount</th>
        <th>Status</th>
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
      </tr>
      <% }
      } else { %>
      <tr>
        <td colspan="5">No bookings found.</td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </section>
</div>
<script>
  // Define base fares for different car types
  const carTypeMultipliers = {
    "Economy": 1.0,
    "Standard": 1.5,
    "Luxury": 2.0
  };

  // Simulated distance-based fare calculation (in LKR)
  const distanceFareRates = {
    "Colombo-Gampaha": 500, "Colombo-Kalutara": 800, "Colombo-Kandy": 1500, "Colombo-Matale": 1700, "Colombo-Nuwara Eliya": 2000,
    "Colombo-Galle": 2000, "Colombo-Matara": 2500, "Colombo-Hambantota": 3000,
    "Colombo-Jaffna": 6000, "Colombo-Kilinochchi": 5800, "Colombo-Mannar": 5500, "Colombo-Mullaitivu": 6200, "Colombo-Vavuniya": 5000,
    "Colombo-Trincomalee": 4000, "Colombo-Batticaloa": 4200, "Colombo-Ampara": 4500,
    "Colombo-Puttalam": 2200, "Colombo-Kurunegala": 1800,
    "Colombo-Anuradhapura": 3200, "Colombo-Polonnaruwa": 3500,
    "Colombo-Badulla": 2800, "Colombo-Monaragala": 3000,
    "Colombo-Ratnapura": 1200, "Colombo-Kegalle": 1000,

    "Gampaha-Kalutara": 700, "Gampaha-Kandy": 1300, "Gampaha-Matale": 1500, "Gampaha-Nuwara Eliya": 1800,
    "Gampaha-Galle": 1800, "Gampaha-Matara": 2300, "Gampaha-Hambantota": 2800,

    "Kandy-Matale": 500, "Kandy-Nuwara Eliya": 900,
    "Kandy-Galle": 2700, "Kandy-Matara": 2900, "Kandy-Hambantota": 3200,
    "Kandy-Jaffna": 5200, "Kandy-Kilinochchi": 5000, "Kandy-Mannar": 4700, "Kandy-Mullaitivu": 5500, "Kandy-Vavuniya": 4200,
    "Kandy-Trincomalee": 2200, "Kandy-Batticaloa": 2400, "Kandy-Ampara": 2700,
    "Kandy-Puttalam": 2000, "Kandy-Kurunegala": 1200,
    "Kandy-Anuradhapura": 2500, "Kandy-Polonnaruwa": 1800,
    "Kandy-Badulla": 1300, "Kandy-Monaragala": 1500,
    "Kandy-Ratnapura": 1100, "Kandy-Kegalle": 900,

    "Galle-Matara": 700, "Galle-Hambantota": 1200,
    "Jaffna-Kilinochchi": 500, "Jaffna-Mannar": 1500, "Jaffna-Mullaitivu": 1800, "Jaffna-Vavuniya": 2200,
    "Trincomalee-Batticaloa": 900, "Trincomalee-Ampara": 1200,
    "Puttalam-Kurunegala": 800, "Anuradhapura-Polonnaruwa": 700,
    "Badulla-Monaragala": 600, "Ratnapura-Kegalle": 500
  };

  function calculateFare() {
    let pickup = document.getElementById("pickupLocation").value;
    let destination = document.getElementById("destination").value;
    let carType = document.querySelector("select[name='carType']").value;
    let fareField = document.getElementById("fareAmount");

    if (pickup && destination && carType) {
      let routeKey = pickup + "-" + destination;
      let reverseRouteKey = destination + "-" + pickup;
      let baseFare = distanceFareRates[routeKey] || distanceFareRates[reverseRouteKey] || 1000; // Default fare if route not defined

      let finalFare = baseFare * carTypeMultipliers[carType];
      fareField.value = finalFare.toFixed(2);
    }
  }

  // Attach event listeners to dropdowns for auto fare calculation
  document.getElementById("pickupLocation").addEventListener("change", calculateFare);
  document.getElementById("destination").addEventListener("change", calculateFare);
  document.querySelector("select[name='carType']").addEventListener("change", calculateFare);
</script>
</body>
</html>

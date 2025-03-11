package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.models.Booking;
import com.example.mega_city_cab.models.Car;
import com.example.mega_city_cab.models.User;
import com.example.mega_city_cab.services.BookingService;
import com.example.mega_city_cab.services.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
public class BookRideController extends HttpServlet {

    private BookingService bookingService = new BookingService();
    private CarService carService = new CarService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get booking details from the form
        String pickupLocation = request.getParameter("pickupLocation");
        String destination = request.getParameter("destination");
        String carType = request.getParameter("carType");
        int userId = user.getId();

        System.out.println("User ID: " + userId);
        System.out.println("Pickup: " + pickupLocation + ", Destination: " + destination + ", Car Type: " + carType);

        // Retrieve fare from request
        String fareStr = request.getParameter("fareAmount");
        double fareAmount = Double.parseDouble(fareStr);


        // Lookup an available car by carType
        Car availableCar = carService.findAvailableCarByType(carType);
        if (availableCar == null) {
            response.sendRedirect("user-dashboard.jsp?error=NoAvailableCar");
            return;
        }

        // Create and set up the booking object
        Booking booking = new Booking();
        booking.setPickupLocation(pickupLocation);
        booking.setDropLocation(destination);
        booking.setCarId(availableCar.getCarId());
        booking.setUserId(userId);
        booking.setFare(fareAmount);  // Ensure fare is stored
        booking.setStatus("Pending");

        // Save booking
        bookingService.createBooking(booking);

        // Redirect back to dashboard
        response.sendRedirect("user-dashboard");
    }
}
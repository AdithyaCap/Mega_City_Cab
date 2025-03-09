package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.models.Booking;
import com.example.mega_city_cab.services.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BookingController extends HttpServlet {
    private BookingService bookingService;

    public void init() {
        bookingService = new BookingService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("user_id"));
        int carId = Integer.parseInt(request.getParameter("car_id"));
        String pickupLocation = request.getParameter("pickup_location");
        String dropLocation = request.getParameter("drop_location");
        double fare = Double.parseDouble(request.getParameter("fare"));

        Booking booking = new Booking(0,userId,  carId, pickupLocation, dropLocation, fare);

        boolean success = bookingService.createBooking(booking);
        if (success) {
            response.sendRedirect("customer-user-dashboard.jsp?message=Booking Successful");
        } else {
            response.sendRedirect("customer-user-dashboard.jsp?error=Booking Failed");
        }
    }

}

package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.models.Booking;
import com.example.mega_city_cab.models.User;
import com.example.mega_city_cab.services.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class UserDashboardController extends HttpServlet {

    private BookingService bookingService = new BookingService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Redirect to login if the user is not logged in
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch all bookings and in-progress bookings
        List<Booking> allBookings = bookingService.getBookingsByUsername(user.getId());
        List<Booking> inProgressBookings = bookingService.getInProgressBookingsByUserId(user.getId());

        // Set attributes for JSP
        request.setAttribute("bookings", allBookings);
        request.setAttribute("inProgressBookings", inProgressBookings);

        // Forward to the user dashboard JSP
        request.getRequestDispatcher("user-dashboard.jsp").forward(request, response);
    }
}
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

        // Check if session is null or user is not logged in
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

            List<Booking> bookings = bookingService.getBookingsByUsername(user.getId());

            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("user-dashboard.jsp").forward(request, response);
    }
}
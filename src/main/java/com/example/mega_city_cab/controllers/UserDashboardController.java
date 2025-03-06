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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Check if the user is logged in
        String action = request.getParameter("action");
        switch (action) {
            case "view":
                // Get the logged-in user's username
                User username = (User) session.getAttribute("user");

                // Fetch booking history for this user
                List<Booking> bookings = bookingService.getBookingsByUsername(String.valueOf(username.getId()));

                // Set bookings in request attribute
                request.setAttribute("bookings", bookings);

                // Forward to user dashboard page
                request.getRequestDispatcher("user-dashboard.jsp").forward(request, response);
        }




    }
}
package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.models.Booking;
//import com.example.mega_city_cab.models.Driver;
import com.example.mega_city_cab.models.User;
import com.example.mega_city_cab.services.BookingService;
//import com.example.mega_city_cab.services.DriverService;
import com.example.mega_city_cab.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DriverController extends HttpServlet {
    private BookingService bookingService;
    private UserService userService;


    public void init() {
        bookingService = new BookingService();
        userService = new UserService();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp?error=" + URLEncoder.encode("Please login first", StandardCharsets.UTF_8));
            return;
        }

        User loggedUser = (User) session.getAttribute("user");

        if (!"Driver".equals(loggedUser.getRole())) {
            response.sendRedirect("login.jsp?error=" + URLEncoder.encode("Unauthorized access", StandardCharsets.UTF_8));
            return;
        }

        // Retrieve Pending and In-Progress Bookings
        List<Booking> pendingBookings = bookingService.getAllPendingBookings();
        List<Booking> inProgressBookings = bookingService.getAllInProgressBookings(); // Fix: Avoid duplicate doGet

        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("pendingBookings", pendingBookings);
        request.setAttribute("inProgressBookings", inProgressBookings);

        request.getRequestDispatcher("driver-dashboard.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp?error=" + URLEncoder.encode("Please login first", StandardCharsets.UTF_8));
            return;
        }

        User loggedUser = (User) session.getAttribute("user");

        if (!"Driver".equals(loggedUser.getRole())) {
            response.sendRedirect("login.jsp?error=" + URLEncoder.encode("Unauthorized access", StandardCharsets.UTF_8));
            return;
        }

        String action = request.getParameter("action");

        if ("updateBookingStatus".equals(action)) {
            try {
                int bookingId = Integer.parseInt(request.getParameter("bookingId"));
                String newStatus = request.getParameter("status");

                boolean updated = bookingService.updateBookingStatus(bookingId, newStatus);

                if (updated) {
                    response.sendRedirect("driver-dashboard?message=" + URLEncoder.encode("Booking status updated successfully", StandardCharsets.UTF_8));
                } else {
                    response.sendRedirect("driver-dashboard?error=" + URLEncoder.encode("Failed to update booking status", StandardCharsets.UTF_8));
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("driver-dashboard?error=" + URLEncoder.encode("Invalid booking ID", StandardCharsets.UTF_8));
            }
        }
    }
}
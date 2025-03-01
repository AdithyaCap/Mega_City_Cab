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

public class CustomerDashboardController extends HttpServlet {

    private BookingService bookingService;

    @Override
    public void init() {
        bookingService = new BookingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get logged-in user details from session
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Assuming the user object has an ID and we're getting bookings for the logged-in customer
        int userId = ((User) session.getAttribute("user")).getId();

        // Get the list of bookings for the logged-in customer
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);

        // Set the bookings as request attribute
        request.setAttribute("bookings", bookings);

        // Forward the request to customer dashboard page
        request.getRequestDispatcher("customer-user-dashboard.jsp").forward(request, response);
    }

}

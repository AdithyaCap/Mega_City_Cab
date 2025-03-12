package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.services.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.mega_city_cab.dao.BookingDAO;


import java.io.IOException;

public class DeleteBookingServlet extends HttpServlet {

    private BookingDAO bookingDAO;

    public void init() {
        bookingDAO = new BookingDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int bookingId = Integer.parseInt(idParam);
                boolean success = bookingDAO.deleteBooking(bookingId);

                if (success) {
                    response.sendRedirect("admin-dashboard"); // Refresh dashboard after deletion
                } else {
                    response.getWriter().println("Error deleting booking.");
                }

            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid Booking ID.");
            }
        } else {
            response.getWriter().println("Booking ID is required.");
        }
    }
}

package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {

    private UserDAO userDAO;


    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int userId = Integer.parseInt(idParam);
                boolean success = userDAO.deleteUser(userId);

                if (success) {
                    response.sendRedirect("admin-dashboard"); // Refresh dashboard after deletion
                } else {
                    response.getWriter().println("Error deleting user.");
                }

            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid User ID.");
            }
        } else {
            response.getWriter().println("User ID is required.");
        }
    }
}
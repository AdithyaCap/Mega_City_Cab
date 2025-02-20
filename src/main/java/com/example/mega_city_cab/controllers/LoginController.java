package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.models.User;
import com.example.mega_city_cab.services.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginController extends HttpServlet {

    private LoginService loginService;

    @Override
    public void init() {
        loginService = new LoginService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = loginService.authenticateUser(username, password);

        if (user != null) {
            // If authentication is successful, store user information in the session
            request.getSession().setAttribute("user", user);
            response.sendRedirect("dashboard.jsp");  // Redirect to the dashboard after login
        } else {
            // If authentication fails, set an error message and redirect back to login page
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For GET requests, just forward to login page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}

package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.models.User;
import com.example.mega_city_cab.services.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginController extends HttpServlet {

    private LoginService loginService;

    @Override
    public void init() {
        loginService = new LoginService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Login attempt received");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        User user = loginService.authenticateUser(username, password);

        if (user != null) {
            System.out.println("User authenticated: " + user.getUsername() + " | Role: " + user.getRole());

            // Create session and set user
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect based on role
            switch (user.getRole()) {
                case "Admin":
                    response.sendRedirect("admin-dashboard");
                    break;
                case "User":
                    response.sendRedirect("user-dashboard");
                    break;
                case "Driver":
                    response.sendRedirect("driver-dashboard");
                    break;
                default:
                    response.sendRedirect("default-user-dashboard.jsp");
                    break;
            }
        } else {
            System.out.println("Invalid credentials!");

            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
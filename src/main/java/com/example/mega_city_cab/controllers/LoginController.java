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


    public void init() {
        loginService = new LoginService();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Login user");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = loginService.authenticateUser(username, password);

        if (user != null) {
            // If authentication is successful, store user information in the session
            request.getSession().setAttribute("user", user);
//            response.sendRedirect("admin-user-dashboard.jsp");  // Redirect to the dashboard after login
            String userRole = user.getRole(); // Assuming the user object has a getRole() method

            System.out.println(userRole);

            if ("Admin".equalsIgnoreCase(userRole)) {
                // Redirect to the admin dashboard
                response.sendRedirect("admin-dashboard.jsp");
            } else if ("User".equalsIgnoreCase(userRole)) {
                // Redirect to the regular user dashboard
                response.sendRedirect("user-dashboard.jsp");
            } else {
                // Redirect to a default dashboard or error page if the role is unknown
                response.sendRedirect("default-user-dashboard.jsp");
            }
        } else {
            // If authentication fails, set an error message and redirect back to login page
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For GET requests, just forward to login page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}

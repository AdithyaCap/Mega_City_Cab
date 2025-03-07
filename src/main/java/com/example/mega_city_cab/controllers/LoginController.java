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

            request.getSession().setAttribute("user", user);
            String userRole = user.getRole();
            System.out.println(userRole);

            if ("Admin".equalsIgnoreCase(userRole)) {
                response.sendRedirect("admin-dashboard.jsp");
            } else if ("User".equalsIgnoreCase(userRole)) {
                response.sendRedirect("user-dashboard.jsp");
            } else if ("Driver".equalsIgnoreCase(userRole)) {
                response.sendRedirect("driver-dashboard.jsp");
            } else {
                response.sendRedirect("default-user-dashboard.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}

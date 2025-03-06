package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.models.User;
import com.example.mega_city_cab.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserRegistrationController extends HttpServlet {
    private UserService userService;


    public void init() {
        userService = new UserService();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String phoneNumber = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create a generic User object (not customer-specific)
        User user = new User(username, address, nic, phoneNumber, email, password);
        user.setUsername(username);
        user.setAddress(address);
        user.setNic(nic);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setPassword(password);

        boolean success = userService.registerUser(user);
        if (success) {
            response.sendRedirect("login.jsp?message=Registration Successful");
        } else {
            response.sendRedirect("register.jsp?error=Registration Failed or Email Already Exists");
        }
    }
}
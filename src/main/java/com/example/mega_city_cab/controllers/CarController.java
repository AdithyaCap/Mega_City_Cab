package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.models.Car;
import com.example.mega_city_cab.services.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CarController extends HttpServlet {
    private CarService carService;

    public void init() {
        carService = new CarService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carModel = request.getParameter("car_model");
        String carNumber = request.getParameter("car_number");
        String carType = request.getParameter("car_type");
        String status = request.getParameter("status");

        Car car = new Car(0, carModel, carNumber, carType, status);

        boolean success = carService.addCar(car);
        if (success) {
            response.sendRedirect("admin-user-dashboard.jsp?message=Car Added Successfully");
        } else {
            response.sendRedirect("admin-user-dashboard.jsp?error=Car Addition Failed");
        }
    }
}
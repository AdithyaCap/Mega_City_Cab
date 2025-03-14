package com.example.mega_city_cab.controllers;

import com.example.mega_city_cab.models.Booking;
import com.example.mega_city_cab.models.Car;
//import com.example.mega_city_cab.models.Driver;
import com.example.mega_city_cab.models.User;
import com.example.mega_city_cab.services.BookingService;
import com.example.mega_city_cab.services.CarService;
//import com.example.mega_city_cab.services.DriverService;
import com.example.mega_city_cab.services.UserService;
import com.mysql.cj.jdbc.Driver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class AdminDashboardController extends HttpServlet {


    private BookingService bookingService;
    private UserService UserService;
    private CarService carService;
    private UserService driverService;


    public void init() {
        bookingService = new BookingService();
        UserService = new UserService();
        carService = new CarService();
        driverService = new UserService();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get logged-in admin details from session
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null || !"Admin".equals(((User) session.getAttribute("user")).getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch data for the dashboard
        List<Booking> bookings = bookingService.getAllBookings();
        List<User> users = UserService.getAllUsers();
        List<Car> cars = carService.getAllCars();
        List<User> drivers = driverService.getAllDrivers();


        // Set attributes for display
        request.setAttribute("bookings", bookings);
        request.setAttribute("customers", users);
        request.setAttribute("cars", cars);
        request.setAttribute("drivers", drivers);

        // Forward to the admin dashboard page
        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);

    }


}

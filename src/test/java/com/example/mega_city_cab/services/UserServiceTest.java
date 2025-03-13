package com.example.mega_city_cab.services;

import com.example.mega_city_cab.dao.UserDAO;
import com.example.mega_city_cab.models.User;
import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    public UserService userService = new UserService();
    public UserDAO userDAO = new UserDAO();

    @Test
    void getAllDrivers() {
       List<User> drivers = userService.getAllDrivers();
       assertNotNull(drivers);
    }

    @Test
    void registerUser() {
        // Create a new user
        User newUser = new User("john", "123 Main St", "NIC123", "123456789", "john@example.com", "password123", "ADMIN");

        // Call the method
        boolean result = userService.registerUser(newUser);

        assertTrue(result);
    }

    @Test
    void getAllUsers() {
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
    }
}
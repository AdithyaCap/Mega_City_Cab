package com.example.mega_city_cab.services;

import com.example.mega_city_cab.dao.UserDAO;
import com.example.mega_city_cab.models.User;
import com.example.mega_city_cab.utils.DatabaseConnection;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public List<User> getAllDrivers() {
        return userDAO.getAllDrivers();
    }


    public User getDriverByUsername(String username) {
        return null;
    }

    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }


}
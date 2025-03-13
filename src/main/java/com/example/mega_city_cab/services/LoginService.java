package com.example.mega_city_cab.services;

import com.example.mega_city_cab.dao.UserDAO;
import com.example.mega_city_cab.models.User;

public class LoginService {
    private UserDAO userDAO;

    public LoginService() {
        userDAO = new UserDAO();
    }

    public User authenticateUser(String username, String password) {
        // Call DAO to get user from the database and verify credentials
        return userDAO.authenticate(username, password);
    }

}

package com.example.mega_city_cab.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    LoginService loginService = new LoginService();

    @Test
    void authenticateUser() {
        LoginService loginService = new LoginService();
        assertNotNull(loginService.authenticateUser("admin", "123"));
    }
}
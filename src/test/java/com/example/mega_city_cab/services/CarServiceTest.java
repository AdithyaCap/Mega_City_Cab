package com.example.mega_city_cab.services;

import com.example.mega_city_cab.models.Car;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    CarService carService = new CarService();

    @Test
    void getAllCars() {
        List<Car> result = carService.getAllCars();
    }
}
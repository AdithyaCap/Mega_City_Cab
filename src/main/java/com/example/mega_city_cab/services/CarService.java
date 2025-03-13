package com.example.mega_city_cab.services;

import com.example.mega_city_cab.dao.CarDAO;
import com.example.mega_city_cab.models.Car;

import java.util.List;
public class CarService {

    private CarDAO carDAO;

    public CarService() {
        this.carDAO = new CarDAO();
    }

    public boolean addCar(Car car) {
        return carDAO.addCar(car);
    }

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCarById(int id) {
        return carDAO.getCarById(id);
    }


    public Car findAvailableCarByType(String carType) {
        return carDAO.findAvailableCarByType(carType);
    }


    public boolean updateCarAvailability(int id, boolean available) {
        String status = available ? "Available" : "Unavailable";
        return carDAO.updateCarAvailability(id, Boolean.parseBoolean(status));
    }

    public boolean deleteCar(int id) {
        return carDAO.deleteCar(id);
    }
}
package com.example.mega_city_cab.models;

public class Car {
    private int carId;
    private String carModel;
    private String carNumber;
    private String carType;
    private String status;

    // Constructor
    public Car(int carId, String carModel, String carNumber, String carType, String status) {
        this.carId = carId;
        this.carModel = carModel;
        this.carNumber = carNumber;
        this.carType = carType;
        this.status = status;
    }

    // Getters and Setters
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
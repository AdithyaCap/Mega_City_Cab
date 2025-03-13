package com.example.mega_city_cab.dao;

import com.example.mega_city_cab.models.Booking;
import com.example.mega_city_cab.models.Car;
import com.example.mega_city_cab.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private Connection connection;

    public CarDAO() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get database connection.", e);
        }
    }

    public boolean addCar(Car car) {
        String sql = "INSERT INTO cars (car_model, car_number, car_type, status) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, car.getCarModel());
            ps.setString(2, car.getCarNumber());
            ps.setString(3, car.getCarType());
            ps.setString(4, car.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cars";

            try (Connection con = DatabaseConnection.getInstance().getConnection();
                    Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Car car = mapResultSetToCar(rs);
                    cars.add(car);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(cars.size());
            return cars;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

//    public List<Car> getAllCars() {
//        List<Car> cars = new ArrayList<>();
//        String sql = "SELECT * FROM cars";
//
//        System.out.println("Executing SQL Query: " + sql); // Log SQL execution
//
//        try (Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                Car car = mapResultSetToCar(rs);
//                cars.add(car);
//                System.out.println("Fetched Car: " + car); // Print each car fetched
//            }
//
//        } catch (SQLException e) {
//            System.err.println("SQL Exception in getAllCars(): " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        System.out.println("Total Cars fetched from DB: " + cars.size()); // Log the total count
//        System.out.println("Cars fetched from DB: " + cars); // Log final car list
//        return cars;
//    }



    public Car getCarById(int id) {
        String sql = "SELECT * FROM cars WHERE car_id = ?";
        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToCar(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateCarStatus(int id, String status) {
        String sql = "UPDATE cars SET status = ? WHERE car_id = ?";
        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCar(int id) {
        String sql = "DELETE FROM cars WHERE car_id = ?";
        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Car findAvailableCarByType(String carType) {
        String sql = "SELECT * FROM cars WHERE car_type = ? AND status = 'Available' LIMIT 1";
        try ( Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, carType);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToCar(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Car mapResultSetToCar(ResultSet rs) throws SQLException {
        return new Car(
                rs.getInt("car_id"),
                rs.getString("car_model"),
                rs.getString("car_number"),
                rs.getString("car_type"),
                rs.getString("status")
        );
    }

    public boolean updateCarAvailability(int id, boolean b) {

        return b;
    }
}
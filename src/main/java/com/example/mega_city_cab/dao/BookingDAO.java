package com.example.mega_city_cab.dao;

import com.example.mega_city_cab.models.Booking;
import com.example.mega_city_cab.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private final Connection connection;

    public BookingDAO() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add a new booking
    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, car_id, pickup_location, drop_location, booking_date, fare, status) VALUES (?, ?, ?, ?, now(), ?, ?)";
        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1,booking.getUserId());
            ps.setInt(2, booking.getCarId());
            ps.setString(3, booking.getPickupLocation());
            ps.setString(4, booking.getDropLocation());
            ps.setDouble(5, booking.getFare());
            ps.setString(6, booking.getStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        System.out.println("bookings");
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Booking booking = mapResultSetToBooking(rs);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(bookings);
        return bookings;
    }

    // Get booking by ID
    public Booking getBookingById(int id) {
        String sql = "SELECT user_id, booking_id,  car_id, booking_date, pickup_location, drop_location, fare, status FROM bookings WHERE booking_id = ?";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToBooking(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete booking by ID
    public boolean deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";
        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get bookings for a specific customer
    public List<Booking> getBookingsByUserId(int userid) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT user_id, booking_id,  car_id, booking_date, pickup_location, drop_location, fare, status FROM bookings WHERE user_id = ?";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = mapResultSetToBooking(rs);
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    // Utility method to map ResultSet to Booking object
    private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setUserId(rs.getInt("user_id"));
        booking.setId(rs.getInt("booking_id"));
        booking.setCarId(rs.getInt("car_id"));
        booking.setBookingDate(rs.getTimestamp("booking_date"));
        booking.setPickupLocation(rs.getString("pickup_location"));
        booking.setDropLocation(rs.getString("drop_location"));
        booking.setFare(rs.getDouble("fare"));
        booking.setStatus(rs.getString("status"));
        return booking;
    }
}
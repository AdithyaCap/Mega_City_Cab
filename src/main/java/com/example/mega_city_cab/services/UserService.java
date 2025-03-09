package com.example.mega_city_cab.services;

import com.example.mega_city_cab.models.User;
import com.example.mega_city_cab.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // ✅ Register User with Role
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, address, nic, phone_number, email, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getNic());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());
            stmt.setString(7, user.getRole()); // ✅ Ensure role is inserted

            System.out.println("Registering User - Role: " + user.getRole()); // Debugging log

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Retrieve All Users (Optional Improvement)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             var rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("address"),
                        rs.getString("nic"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // ✅ Delete User (Optional Improvement)
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
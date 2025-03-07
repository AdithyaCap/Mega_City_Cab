package com.example.mega_city_cab.models;

public class User {

    private int id;                    // Common for all users
    private String username;           // For login (can be email or custom username)
    private String password;           // For login
    private String role;                // Role like "customer", "admin", "driver"

    // Additional fields for customers (optional for admins, etc.)

    private String address;
    private String nic;
    private String phoneNumber;
    private String email;

    // Constructor for customer (with all fields)
    public User(int id, String username, String address, String nic, String phoneNumber, String email, String password, String role) {
        this.id = id;
        this.setUsername(username);
        this.address = address;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.setUsername(email);  // You can treat email as username for customers
        this.role = role;
    }

    // Constructor for admin/driver (with only basic fields)
    public User(String username, String address, String nic, String phoneNumber, String email, String password, String role) {
        this.id = id;
        this.setUsername(getUsername());
        this.password = this.password;
        this.role = this.role;
    }

    public User(String username, String address, String nic, String phoneNumber, String email, String password) {

        this.username = username;
        this.address = address;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters (for all fields)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getUsername() {
//        return username;
//    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public String getName() {
//        return username;
//    }

//    public void setName(String name) {
//        this.username = name;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
package com.example.mega_city_cab.models;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String address;
    private String nic;
    private String phoneNumber;
    private String email;

    // Constructor for full user details
    public User(int id, String username, String address, String nic, String phoneNumber, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Constructor without ID
    public User(String username, String address, String nic, String phoneNumber, String email, String password, String role) {
        this.username = username;
        this.address = address;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Constructor for authentication
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password) {

    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) {
        System.out.println("Setting role: " + role);
        this.role = role;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
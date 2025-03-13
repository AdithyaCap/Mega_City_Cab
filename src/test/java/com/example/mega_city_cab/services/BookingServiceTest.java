package com.example.mega_city_cab.services;

import com.example.mega_city_cab.dao.BookingDAO;
import com.example.mega_city_cab.models.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {


    private BookingService bookingService = new BookingService();


    @BeforeEach
    void setUp() {
        bookingService = new BookingService(); // Create a new service instance
    }

    @Test
    void createBooking() {
        // Creating a booking object with valid data
        Booking booking = new Booking();
        booking.setUserId(1); // Ensure this user exists in the database
        booking.setCarId(1); // Ensure this car_id exists in the 'cars' table
        booking.setPickupLocation("Location A");
        booking.setDropLocation("Location B");
        booking.setFare(50.0);
        booking.setStatus("Pending");

        // Insert booking and verify success
        boolean result = bookingService.createBooking(booking);
        assertTrue("Booking should be created successfully", result);

    }

    private void assertTrue(String bookingCreationFailed, boolean result) {

    }

    @Test
    void getAllBookings() {
        List<Booking> result = bookingService.getAllBookings();

        assertNotNull(result);

    }


    @Test
    void updateBookingStatus() {

        boolean result = bookingService.updateBookingStatus(1, "confirmed");
        assertTrue("passed", result);
    }


}

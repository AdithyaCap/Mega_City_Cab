package com.example.mega_city_cab.services;

import com.example.mega_city_cab.dao.BookingDAO;
import com.example.mega_city_cab.models.Booking;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    private BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new BookingDAO();
    }

    public boolean createBooking(Booking booking) {
        return bookingDAO.addBooking(booking);
    }


    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    public Booking getBookingById(int id) {
        return bookingDAO.getBookingById(id);
    }



    public List<Booking> getBookingsByUserId(int userId) {
        return List.of();
    }

//    public List<Booking> getBookingsByUsername(int userid) {
//        return bookingDAO.getBookingsByUserId(userid);
//    }

    public List<Booking> getBookingsByDriverId(int id) {

        return List.of();
    }

    public boolean updateBookingStatus(int bookingId, String newStatus) {
        return bookingDAO.updateBookingStatus(bookingId, newStatus);
    }

    public List<Booking> getBookingsByUsername(int userId) {
        return bookingDAO.getBookingsByUserId(userId);

    }
    public List<Booking> getAllPendingBookings() {
        return bookingDAO.getAllPendingBookings();
    }

    public boolean deleteBooking(int bookingId) {
        BookingDAO bookingDAO = new BookingDAO();
        return bookingDAO.deleteBooking(bookingId);
    }

    public List<Booking> getAllInProgressBookings() {
        return bookingDAO.getAllInProgressBookings();
    }

    public List<Booking> getInProgressBookingsByUserId(int userId) {
        List<Booking> allBookings = getBookingsByUsername(userId);
        return allBookings.stream()
                .filter(booking -> "In Progress".equalsIgnoreCase(booking.getStatus()))
                .collect(Collectors.toList());
    }



}
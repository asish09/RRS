package com.example.demo.service;
import java.util.List;

import com.example.demo.exception.NoProperDataException;
import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.model.Booking;

public interface BookingService {
	public Booking getBookingById( int bookingId) throws BookingNotFoundException;
	public Booking addBooking(Booking booking)  throws  NoProperDataException;
	public Booking updateBooking(Booking booking,int bookingId)  throws BookingNotFoundException;
	public String deleteBooking(int bookingId) throws BookingNotFoundException;
	public List<Booking> getAllBooking() throws BookingNotFoundException;
	

}

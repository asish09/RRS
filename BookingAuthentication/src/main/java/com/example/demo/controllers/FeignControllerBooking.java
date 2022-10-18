package com.example.demo.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NoProperDataException;
import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.models.Booking;
import com.example.demo.services.SequenceGeneratorService;
import com.example.demo.util.FiegnClientUtilBooking;

@RestController
@RequestMapping("/booking")
public class FeignControllerBooking {

	@Autowired
	private FiegnClientUtilBooking feignbooking;
	
	
	@Autowired
	private SequenceGeneratorService service;

	@GetMapping("/allbooking") 
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public ResponseEntity<List<Booking>> getAllBooking(@RequestHeader("Authorization") String token) throws BookingNotFoundException
	{
		
		return feignbooking.getAllBooking(token);
		
	}
	
	@GetMapping("/booking/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Booking> getBookingById(@Valid@RequestHeader("Authorization") String token, @PathVariable  int id)
	throws BookingNotFoundException{
		return feignbooking.getBookingById(token,id);
	}
	
	@PostMapping("/addbooking") 
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Booking> addBooking(@RequestBody Booking booking)  throws NoProperDataException
	{
		booking.setBookingId(service.getSequenceNumberForBooking(Booking.SEQUENCE_NAME));
		
		return feignbooking.addBooking(booking);
	}

	@PutMapping("/updatebooking/{id}")
	@PreAuthorize( "hasRole('ADMIN')")
	public ResponseEntity<Booking> updateBooking( @RequestBody Booking booking,@PathVariable int id) throws BookingNotFoundException
	{
	return feignbooking.updateBooking(booking,id);
	}
	
	@DeleteMapping(path="/booking/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> deleteBooking(@Valid @RequestHeader("Authorization") String token,@PathVariable int id) throws BookingNotFoundException {
			return feignbooking.deleteBooking(token,id);
}

	
}

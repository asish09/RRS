package com.example.demo.util;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.models.Booking;

@FeignClient(value ="Booking-Service",url ="http://localhost:8081/booking")
public  interface FiegnClientUtilBooking {
	

	@GetMapping("/allbooking") 
	public ResponseEntity<List<Booking>> getAllBooking(@RequestHeader("Authorization") String token);
	
	
	@GetMapping("/booking/{id}")
	public ResponseEntity<Booking> getBookingById(@RequestHeader("Authorization") String token,@PathVariable int id);
	
	
	@PostMapping("/addbooking") 
	@PreAuthorize(" hasRole('USER')")
	public ResponseEntity<Booking> addBooking(Booking booking); 

	@PutMapping("/updatebooking/{id}")
	public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking,@PathVariable int id);
	
	@DeleteMapping(path="/booking/{id}")
	
	public ResponseEntity<String> deleteBooking(@RequestHeader("Authorization") String token,@PathVariable int id);


	

	
}

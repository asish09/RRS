package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NoProperDataException;
import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.service.BookingServiceImpl;
import com.example.demo.service.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@CrossOrigin(origins ="http://localhost:3000")
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingServiceImpl bookingServiceimpl;
	Logger logger = LoggerFactory.getLogger(BookingController.class);
	@Autowired
	private SequenceGeneratorService service;
	
	@GetMapping("/allbooking") 
	public ResponseEntity<List<Booking>> getAllBooking() throws BookingNotFoundException
	{
		
		List<Booking> booking=bookingServiceimpl.getAllBooking();
		logger.info("starting  of get mapping");
	
		if(booking.size()>0) {
			logger.debug("booking are {}"
					+ booking);
		 return new  ResponseEntity<>(booking,HttpStatus.OK); 
		}
		else {
			logger.debug(" no booking found ");
			 return new  ResponseEntity<>(booking,HttpStatus.NO_CONTENT); 
		}
		
		
	}
	@GetMapping("/booking/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable  int id)
	throws BookingNotFoundException{
		
		Booking booking= bookingServiceimpl.getBookingById(id);
		if(booking!=null){
		  return ResponseEntity.ok().body(booking);
		}
		  else {
			return new ResponseEntity(booking,HttpStatus.NOT_FOUND);
		  }

	}
	@PostMapping("/addbooking") 
	public ResponseEntity<Booking> addBooking(@RequestBody Booking booking)  throws NoProperDataException
		
		{
			
			if(booking!=null) 
			{
				
				booking.setBookingId(service.getSequenceNumberForBooking(Booking.SEQUENCE_NAME));
				bookingServiceimpl.addBooking(booking);
				logger.error("added booking");
				return new ResponseEntity<>(booking,HttpStatus.CREATED);
				
			}
			else
			{
				throw new NoProperDataException("Please fill fields");
				
			}
			
		}
	@DeleteMapping(path="/booking/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable int id) throws BookingNotFoundException {
		int count=1;
		for(int i=1;i>=count;count++)
		{
			if(count==1)
			{
			try {
				bookingServiceimpl.deleteBooking(id);
			} catch (BookingNotFoundException e) {
				logger.error(e.getMessage());
			}
			}
			else
			{
				logger.info("id not found");
			}
		}
			return ResponseEntity.ok(" deleted operation done ");
	
	}


@PutMapping("/updateBooking/{id}")
public Booking updateBooking(@RequestBody Booking booking,@PathVariable int bookingId) throws BookingNotFoundException {
	Booking book = bookingServiceimpl.updateBooking(booking, bookingId);
	return book;
}
	
		

}

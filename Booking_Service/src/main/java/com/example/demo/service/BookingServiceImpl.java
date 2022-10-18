package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.exception.NoProperDataException;
import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@Slf4j

public class BookingServiceImpl implements BookingService{
	@Autowired
	private BookingRepository bookingRepository;
	Logger log = LoggerFactory.getLogger(BookingService.class);
	
	@Override
	public List<Booking> getAllBooking() throws BookingNotFoundException {
		List<Booking> booking =new ArrayList<>();
		return booking =bookingRepository.findAll();
	}
		
	@Override
	public Booking getBookingById(int id) throws BookingNotFoundException {
		Booking book=bookingRepository.findById(id).orElseThrow(()-> new  BookingNotFoundException("booking Not Found"+id));

		return book;
	}

	@Override
	public Booking addBooking(Booking booking) throws NoProperDataException {
		log.info("start");
		if(booking!=null) 
		{
			bookingRepository.save(booking);
			log.debug("booking added");
		}
		else
		{
			throw new NoProperDataException("Please fill fields");
		}
		return booking;	
	}

	@Override
	public Booking updateBooking(Booking book, int bookingId) throws BookingNotFoundException {
		Booking booking=bookingRepository.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("booking not "+bookingId));
		book.setBookingId(book.getBookingId());
		book.setTrainName(book.getTrainName());
     	book.setSource(book.getSource());
		book.setDestination(book.getDestination());
		book.setSeatNum(book.getSeatNum());
		book.setCost(book.getCost());
		book.setBookingDate(book.getBookingDate());
		
		final Booking updatedBooking = bookingRepository.save(book);
		return updatedBooking;
	}

	@Override
	public String deleteBooking(int id) throws BookingNotFoundException {
		log.info("Start delete");
		Optional<Booking> isRemoved = bookingRepository.findById(id);
		if(isRemoved.isPresent())
		{
			bookingRepository.deleteById(id);
			log.debug("deleted successfully {}",isRemoved.get());
		}
		else
		{
			throw new BookingNotFoundException("Id Not Available");
		}
		log.info(" deleted End");
		return " deleted successfully";
	}

	
	
	}


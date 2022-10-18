package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.BookingServiceApplication;
import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = BookingServiceApplication.class)
public class BookingServiceImplTest {

	
	@InjectMocks
	private BookingServiceImpl service;
	
	@Mock
	private BookingRepository repository;
	
	@Test
	void testServiceNotNull() {
		assertThat(service).isNotNull();
	}
	
	@Test
	void testRepositoryNotNull() {
		assertThat(repository).isNotNull();
	}
	
	@Test
	void testGetAllBooking() throws BookingNotFoundException {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		Booking b2=new Booking(2001,"hyd express","hyd","banglore",5,9000,"22-09-2022");
		List<Booking> BookingList=new ArrayList<Booking>();
		BookingList.add(b1);
		BookingList.add(b2);
		when(repository.findAll()).thenReturn(BookingList);
		assertEquals(BookingList,service.getAllBooking());
		
	}
	
	@Test
	void testGetBookingById() throws BookingNotFoundException {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		when(repository.findById(2000)).thenReturn(Optional.of(b1));
		assertEquals(b1,service.getBookingById(2000));
	}
	
	@Test
	void testGetBookingByInvalidId() throws BookingNotFoundException {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		when(repository.findById(2000)).thenReturn(Optional.of(b1));
		try {
			assertThat(service.getBookingById(1)).as("Booking with the id 1 doesn't exist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	void testAddBooking() throws BookingNotFoundException, Exception {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		((List<Booking>) assertThat(service.addBooking(b1)))
		.contains("added successfully....");
	
	}	
	
	@Test
	void testAddBookingAlreadyExists() throws BookingNotFoundException {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		when(repository.findById(2000)).thenReturn(Optional.of(b1));
	try {
		((List<Booking>) assertThat(service.addBooking(b1)))
		.contains("Booking with the id "+b1.getBookingId()+" already exist");
	}catch(Exception e) {
		
	}
	}
	
	
	@Test
	void testupdateBooking() throws BookingNotFoundException {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		when(repository.findById(2000)).thenReturn(Optional.of(b1));
		((List<Booking>) assertThat(service.updateBooking(b1, 2000)))
		.contains("updated successfully....");
	
	}
	
	
	@Test
	void testupdateBookingDoesnotExists() throws BookingNotFoundException {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		when(repository.findById(10)).thenReturn(Optional.of(b1));
	try {
		((List<Booking>) assertThat(service.updateBooking(b1, 10)))
		.contains("Booking with the id "+b1.getBookingId()+" doesn't exist for update");
	}catch(Exception e) {
		
	}
	}
	
	@Test
	void testDeleteBookingById() throws BookingNotFoundException {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		when(repository.findById(2000)).thenReturn(Optional.of(b1));
		assertThat(service.deleteBooking(2000))
		.contains("deleted successfully....");
	}
	
	@Test
	void testDeleteBookingByInvalidId() throws BookingNotFoundException {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		when(repository.findById(2000)).thenReturn(Optional.of(b1));
		try {
			assertThat(service.deleteBooking(111))
			.contains("Booking with the id "+b1.getBookingId()+" doesn't exist");
		}catch(Exception e) {
			
		}
	}

	
	
	
	
	}
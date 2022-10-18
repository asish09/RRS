package com.example.demo.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {

	@MockBean
	private BookingServiceImpl service;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	void testServiceNotNull() {
		assertThat(service).isNotNull();
	}
	
	@Test
	void testMockMvcNotNull() {
		assertThat(mockMvc).isNotNull();
	}
	
	
	@Test
	void testShowBookings() throws Exception {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		Booking b2=new Booking(2001,"hyd express","hyd","banglore",5,9000,"22-09-2022");
		List<Booking> BookingList=new ArrayList<Booking>();
		BookingList.add(b1);
		BookingList.add(b2);
		when(service.getAllBooking()).thenReturn(BookingList);
	mockMvc.perform(get("/allbooking"))
	.andExpect(status().isOk())
	.andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$[*]", hasSize(2)))
	.andExpect(jsonPath("$[0].bookingId").value(2000))
	.andExpect(jsonPath("$[0].trainName").value("talcher express"))
	.andExpect(jsonPath("$[0].source").value("hyd"))
	.andExpect(jsonPath("$[0].destination").value("banglore"))
	.andExpect(jsonPath("$[0].seatNum").value(4))
	.andExpect(jsonPath("$[0].cost").value(3000))
	.andExpect(jsonPath("$[0].bookingDate").value("22-09-2022"));
		
	}
	
	@Test
	void testBookingById() throws Exception {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
	when(service.getBookingById(2000)).thenReturn(b1);
	mockMvc.perform(get("/booking/2000"))
	.andExpect(status().isOk())
	.andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$.id").value(101))
	.andExpect(jsonPath("$.name").value("MI-pro7"))
	.andExpect(jsonPath("$.category").value("mobile"))
	.andExpect(jsonPath("$.price").value(20000));
		
	}
	
	
	@Test
	void testShowBookingInvalidId() throws Exception {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
	when(service.getBookingById(2000)).thenReturn(b1);
	MvcResult result=mockMvc.perform(get("/Bookings/1"))
	.andExpect(status().isOk())
	.andReturn();
	assertThat(result.getResponse().toString())
	.as("Booking with the id 1 doesn't exist");
		
	}
	
	
	@Test
	void testDeleteBooking() throws Exception {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		String s="deleted successfully....";
	when(service.deleteBooking(2000)).thenReturn(s);
	mockMvc.perform(delete("/Bookings/101"))
	.andExpect(status().isOk())
	.andExpect(content().string(s));	
	}
	
	@Test
	void testdeleteBookingInvalidId() throws Exception {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		String s="deleted successfully....";
		when(service.deleteBooking(101)).thenReturn(s);
	MvcResult result=mockMvc.perform(delete("/Bookings/11"))
	.andExpect(status().isOk())
	.andReturn();
	assertThat(result.getResponse().toString())
	.as("Booking with the id 1 doesn't exist");
		
	}
	
	@Test
	void testAddBooking() throws Exception {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		String s="added successfully....";
		when(service.addBooking(b1)).thenReturn(s);
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter  writer=mapper.writer().withDefaultPrettyPrinter();
		String reqstr=writer.writeValueAsString(b1);
	mockMvc.perform(post("/booking/addbooking")
			.contentType("application/json")
			.content(reqstr))
	.andExpect(status().isOk())
	.andExpect(content().string(s));
		
	}
	
	@Test
	void testUpdateBooking() throws Exception {
		Booking b1=new Booking(2000,"talcher express","hyd","banglore",4,3000,"22-09-2022");
		String s="updated successfully....";
		when(service.updateBooking(b1, 2000)).thenReturn(s);
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter  writer=mapper.writer().withDefaultPrettyPrinter();
		String reqstr=writer.writeValueAsString(b1);
	mockMvc.perform(put("/updateBooking/2000")
			.contentType("application/json")
			.content(reqstr))
	.andExpect(status().isOk())
	.andExpect(content().string(s));
		
	}
}
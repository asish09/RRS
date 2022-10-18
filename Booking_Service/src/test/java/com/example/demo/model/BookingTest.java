package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BookingTest {
	

		Booking b1;
		@BeforeEach
		public void before() {
			 b1 = new Booking(2000,"talcher express", "hyd", "banglore", 4, 3000, "22-09-2022");
		}
		
		
		@AfterEach
		public void after() {
			b1=null;
		}
		
		@Test
		void testGetBookingId() {
		assertEquals(2000, b1.getBookingId());
		}

		@Test
		void testGetTrainName() {
			assertEquals("talcher express", b1.getTrainName());
		}

		@Test
		void testGetSource() {
			assertEquals("hyd", b1.getSource());
		}
		
		@Test
         void testGetDestination() {
			assertEquals("banglore",b1.getDestination());
		}
		
		@Test
		void testGetSeatNum() {
			assertEquals(4, b1.getSeatNum());
		}

		@Test
		void testGetCost() {
			assertEquals(3000, b1.getCost());
		}
		@Test
		void testGetBookingDate()
		{
			assertEquals("22-09-2022", b1.getBookingDate());
		}
		

		@Test
		void testSetBookingId() {
			b1.setBookingId(2000);
			assertEquals(2000, b1.getBookingId());
		}

		@Test
		void testSetTrainName() {
			b1.setTrainName("talcher express");
			assertEquals("talcher express", b1.getTrainName());
		}

		@Test
		void testSetSource() {
			b1.setSource("hyd");
			assertEquals("hyd",b1.getSource());
		}

		@Test
		void testSetDestination() {
			b1.setDestination("banglore");
			assertEquals("banglore",b1.getDestination());
		}
		@Test
		void testSetSeatNum() {
			b1.setSeatNum(4);
			assertEquals(4, b1.getSeatNum());
		}
		
		@Test
		void testSetCost() {
			b1.setCost(3000);
			assertEquals(3000, b1.getCost());
		}
		@Test
		void testSetBookingDate() {
			b1.setBookingDate("22-09-2022");
			assertEquals("22-09-2022", b1.getBookingDate());
		}

	}


	
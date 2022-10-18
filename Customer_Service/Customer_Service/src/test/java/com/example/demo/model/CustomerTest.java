package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.entity.Customer;


public class CustomerTest {
	

		Customer c1;
		@BeforeEach
		public void before() {
			 c1 = new Customer(2001,"saiyog", "saiyog@gmail.com", 997665775 , "Hyderabad");
		}
		
		
		@AfterEach
		public void after() {
			c1=null;
		}
		
		@Test
		void testGetCustomerId() {
		assertEquals(2001, c1.getCust_id());
		}

		@Test
		void testGetCustomerUsername() {
			//assertEquals("saiyog", c1.getUsername());
		}

		@Test
		void testGetEmailId() {
			assertEquals("saiyog@gmail.com", c1.getEmail());
		}
		
		@Test
		void testGetMobileNo() {
			assertEquals(997665775, c1.getContact_No());
		}

		@Test
		void testGetAddress()
		{
			assertEquals("Hyderabad", c1.getAddress());
		}
		

		@Test
		void testSetCustomerId() {
			c1.setCust_id(2001);
			assertEquals(2001, c1.getCust_id());
		}

		@Test
		void testSetUsername() {
			c1.setUsername("saiyog");
			//assertEquals("talcher express", c1.getUsername());
		}

		@Test
		void testSetEmailId() {
			c1.setEmail("saiyog@gmail.com");
			assertEquals("saiyog@gmail.com",c1.getEmail());
		}

		@Test
		void testSetMobileNo() {
			c1.getContact_No();
			assertEquals(997665775, c1.getContact_No());
		}
		
		@Test
		void testSetAddress() {
			c1.setAddress("Hyderabad");
			assertEquals("Hyderabad", c1.getAddress());
		}

	}
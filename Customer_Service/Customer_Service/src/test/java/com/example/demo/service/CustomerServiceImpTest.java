package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.CustomerTest;
import com.example.demo.repository.CustomerRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerServiceImpTest {

	
	@InjectMocks
	private CustomerServiceImp service;
	
	@Mock
	private CustomerRepository repository;
	
	@Test
	void testServiceNotNull() {
		assertThat(service).isNotNull();
	}
	
	@Test
	void testRepositoryNotNull() {
		assertThat(repository).isNotNull();
	}
	
	@Test
	void testgetAllCustomer() throws CustomerNotFoundException {
		Customer c1=new Customer(2001,"saiyog","saiyog@gmail.com",997665775 ,"Hyderabad");
		Customer c2=new Customer(2002,"nagari","nagari@gmail.com",997665775 , "Bangalore");
		List<Customer> CustomerList=new ArrayList<Customer>();
		CustomerList.add(c1);
		CustomerList.add(c2);
		when(repository.findAll()).thenReturn(CustomerList);
		assertEquals(CustomerList,service.getAllCustomers());
		
	}
	
	@Test
	void testGetCustomerById() throws CustomerNotFoundException {
		Customer c1=new Customer(2001,"saiyog","saiyog@gmail.com",997665775 ,"Hyderabad");
		when(repository.findById(2001)).thenReturn(Optional.of(c1));
		assertEquals(c1,service.getCustomerById(2001));
	}
	
	@Test
	void testGetCustomerByInvalidId() throws CustomerNotFoundException {
		Customer c1=new Customer(2001,"saiyog","saiyog@gmail.com",997665775 ,"Hyderabad");
		when(repository.findById(2001)).thenReturn(Optional.of(c1));
		try {
			assertThat(service.getCustomerById(1)).as("Customer with the id 1 doesn't exist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Test
	void testAddCustomer() throws CustomerNotFoundException, Exception {
		Customer c1=new Customer(2001,"saiyog","saiyog@gmail.com",997665775 ,"Hyderabad");
		((List<Customer>) assertThat(service.addCustomer(c1)))
		.contains("added successfully....");
	
	}	
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Test
	void testAddCustomerAlreadyExists() throws CustomerNotFoundException {
		Customer c1=new Customer(2001,"saiyog","saiyog@gmail.com",997665775 ,"Hyderabad");
		when(repository.findById(2001)).thenReturn(Optional.of(c1));
	try {
		((List<Customer>) assertThat(service.addCustomer(c1)))
		.contains("Customer with the id "+c1.getCust_id()+" already exist");
	}catch(Exception e) {
		
	}
	}
	
	@Test
	void testDeleteCustomerById() throws CustomerNotFoundException {
		Customer c1=new Customer(2001,"saiyog","saiyog@gmail.com",997665775 ,"Hyderabad");
		when(repository.findById(2001)).thenReturn(Optional.of(c1));
		assertThat(service.deleteCustomer(2000))
		.contains("deleted successfully....");
	}
	
	@Test
	void testDeleteCustomerByInvalidId() throws CustomerNotFoundException {
		Customer c1=new Customer(2001,"saiyog","saiyog@gmail.com",997665775 ,"Hyderabad");
		when(repository.findById(2001)).thenReturn(Optional.of(c1));
		try {
			assertThat(service.deleteCustomer(111))
			.contains("Customer with the id "+c1.getCust_id()+" doesn't exist");
		}catch(Exception e) {
			
		}
	}
	
	
	}
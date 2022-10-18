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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.NoProperDataException;
import com.example.demo.models.Customer;
import com.example.demo.services.SequenceGeneratorService;
import com.example.demo.util.FiegnClientUtilCustomer;
@RestController
@RequestMapping("/api/v2")
public class FeignControllerCustomer {
	

	@Autowired
	private FiegnClientUtilCustomer feigncustomer;
	
	
	@Autowired
	private SequenceGeneratorService service;

	@GetMapping("/allcustomers") 
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<List<Customer>> getAllCustomer(@RequestHeader("Authorization") String token) throws CustomerNotFoundException
	{
		
		return feigncustomer.getAllCustomer(token);
		
	}
	
	@GetMapping("/customers/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Customer> getCustomerById(@Valid @RequestHeader("Authorization") String token,@PathVariable  Integer id)
	throws CustomerNotFoundException{
		return feigncustomer.getCustomerById(token,id);
	}
	
	@PostMapping("/addCustomers") 
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer)  throws NoProperDataException
	{
		customer.setCust_id(service.getSequenceNumberForCustomer(Customer.SEQUENCE_NAME));
		return feigncustomer.addCustomer(customer);
	}


	@DeleteMapping(path="/customers/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCustomer(@Valid @RequestHeader("Authorization") String token,@PathVariable int id) throws CustomerNotFoundException {
			return feigncustomer.deleteCustomer(token,id);
	}

}

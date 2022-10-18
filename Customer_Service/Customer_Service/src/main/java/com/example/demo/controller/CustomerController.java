package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.NoProperDataException;
import com.example.demo.service.CustomerServiceImp;
import com.example.demo.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins ="http://localhost:3000")
@RequestMapping("api/v2")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImp customerServiceimpl;
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private SequenceGeneratorService service;
		

	
	@GetMapping("/allcustomers") 
	public ResponseEntity<List<Customer>> getAllCustomer() throws CustomerNotFoundException
	{
		
		List<Customer> customers=customerServiceimpl.getAllCustomers();
		logger.info("starting  of get mapping");
	
		if(customers.size()>0) {
			logger.debug("customers are {}"
					+ customers);
		 return new  ResponseEntity<>(customers,HttpStatus.OK); 
		}
		else {
			logger.debug(" no customers found ");
			 return new  ResponseEntity<>(customers,HttpStatus.NO_CONTENT); 
		}
	}
	
	
	

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@Valid @PathVariable  int id)
	throws CustomerNotFoundException	
		
		{
			logger.info("starting  of get mapping");
			Customer customers=customerServiceimpl.getCustomerById(id);
		
			if(customers.getCustomerId()!=0) {
				logger.debug("customers are {}"
						+ customers);
			 return new  ResponseEntity<>(customers,HttpStatus.OK); 
			}
			else {
				logger.debug(" no customers found ");
				 return new  ResponseEntity<>(customers,HttpStatus.NOT_FOUND); 
			}
		}
		
	
	@PostMapping("/addCustomers") 
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer)  throws NoProperDataException
	{
		if(customer!=null) 
		{
			
			customer.setCustomerId(service.getSequenceNumberForCustomer(Customer.SEQUENCE_NAME));
			customerServiceimpl.addCustomer(customer);
			logger.error("added customer");
			return new ResponseEntity<>(customer,HttpStatus.CREATED);
			
		}
		else
		{
			throw new NoProperDataException("Please fill fields");
		}
		
		
	}

	

	@DeleteMapping(path="/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@Valid @PathVariable int id) throws CustomerNotFoundException {
		int count=1;
		for(int i=1;i>=count;count++)
		{
			if(count==1)
			{
			try {
				customerServiceimpl.deleteCustomer(id);
			} catch (CustomerNotFoundException e) {
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
		
	}


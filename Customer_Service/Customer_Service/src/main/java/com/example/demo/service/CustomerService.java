package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.NoProperDataException;


	public interface CustomerService {
		public  List<Customer> getAllCustomers();
		public Customer getCustomerById(int id) throws CustomerNotFoundException;
		public Customer addCustomer( Customer customer) throws NoProperDataException;
		public String deleteCustomer(int id) throws CustomerNotFoundException;
	}



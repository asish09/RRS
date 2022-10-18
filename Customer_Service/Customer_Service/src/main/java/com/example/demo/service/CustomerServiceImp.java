package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.NoProperDataException;
import com.example.demo.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImp implements CustomerService {

	
	@Autowired
	private CustomerRepository customerRepository;
	Logger logger = LoggerFactory.getLogger(CustomerServiceImp.class);
	
	@Override
	public List<Customer> getAllCustomers()  {
		logger.info("get all customers");
		return customerRepository.findAll();
		
	}
	
	

	
	@Override
	public Customer addCustomer(Customer customer) throws NoProperDataException {
		
		if(customer!=null) 
		{
			customerRepository.save(customer);
			logger.debug("customer added {}",customer);
			
		}
		else
		{
			throw new NoProperDataException("Please fill fields");
		}
		return customer;
	}
	

	
	@Override
	public Customer getCustomerById(int id) throws CustomerNotFoundException {
		Customer customers=customerRepository.findById(id).orElseThrow(()-> new  CustomerNotFoundException("customer Not Found"+id));
		logger.debug("customers getbyid {}",customers);
		return customers;
	}

	



	@Override
	public String deleteCustomer(int id) throws CustomerNotFoundException {
		logger.info("Start delete");
		Optional isRemoved =customerRepository.findById(id);
		if(isRemoved.isPresent())
		{
			customerRepository.deleteById(id);
			logger.debug("deleted successfully {}",isRemoved.get());
		}
		else
		{
			throw new CustomerNotFoundException("Id Not Available");
		}
		logger.info(" deleted End");
		return " deleted successfully";
	}

}

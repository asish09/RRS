/*package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.controller.CustomerController;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
class CustomerControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private  CustomerServiceImp customerServiceimpl;
	

	@MockBean
	private CustomerRepository customerRepository;
	
    @Test
     public List<Customer> getAllCustomers() throws Exception{
    	List<Customer> cust= new ArrayList<>();
    	cust.add(new Customer());
    	cust.add(new Customer());
    	Mockito.when(customerServiceimpl.getAllCustomers());
    	
    	String url ="api/v2/allcustomers";
  MvcResult mvcResult=  mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
  String actualJsonResponse=mvcResult.getResponse().getContentAsString();
  System.out.println(actualJsonResponse);
  String exceptedJsonResponse = objectMapper.writeValueAsString(cust);
  assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);
	return cust;
    	
    }
	
}*/

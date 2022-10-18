package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.CustomerServiceApplication;
import com.example.demo.controller.CustomerControllerTest;
import com.example.demo.model.CustomerTest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CustomerService;
import com.example.demo.service.CustomerServiceImpTest;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CustomerServiceApplication.class)

public class CustomerControllerTest {
	@InjectMocks
	private CustomerController customercontroller;

	@MockBean
	private CustomerService customerService;
	@MockBean
	private CustomerRepository customerrepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void CreateFlight() throws Exception {
		//Customer f = new Customer(1, "kolkata", "mumbai", "22/7/22", 10000, "indigo");

		mockMvc.perform(
				post("/add").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(f)))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getAllCustomers() throws Exception {
		//List<Flights> cust = new ArrayList<>();
		//cust.add(new Flights(1, "kolkata", "pune", "22/7/22", 5000, "indigo"));

		//when(iflightService.getAllFlights()).thenReturn(cust);

		String url = "/viewAll";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println(actualJsonResponse);
		//String exceptedJsonResponse = objectMapper.writeValueAsString(cust);
		//assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);

	}

	@Test
	public void getCustomerById() throws Exception {
		//Customers f = new Customers();
		/*f.setId(11);
		f.setFrom("kolkata");
		f.setTo("Mumbai");
		f.setDate("8/7/22");
		f.setFare(20000);
		f.setAirline("Indigo");*/

		//when(iflightService.getFlightById(11)).thenReturn(f);
		String url = "/View/11";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println(actualJsonResponse);
		//String exceptedJsonResponse = objectMapper.writeValueAsString(f);
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);

	}

	@Test
	public void updateFlight() throws JsonProcessingException, Exception {
		//Flights currentFlight = new Flights(1, "Kolkata", "mumbai", "22/7/22", 10000, "indigo");
		//Flights updatedFlight = new Flights(1, "Kolkata", "pune", "22/7/22", 10000, "indigo");

		//when(iflightService.getFlightById(1)).thenReturn(currentFlight);
		//when(iflightService.updateFlight(currentFlight, 1)).thenReturn(updatedFlight);

		mockMvc.perform(put("/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedFlight))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void deleteFlight() throws JsonProcessingException, Exception {
		//Flights currentFlight = new Flights(1, "Kolkata", "mumbai", "22/7/22", 10000, "indigo");

		//when(iflightService.getFlightById(1)).thenReturn(currentFlight);

		mockMvc.perform(delete("/delete/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());

	}

}
//@WithMockUser(username = "test", password = "test", roles = "USER")
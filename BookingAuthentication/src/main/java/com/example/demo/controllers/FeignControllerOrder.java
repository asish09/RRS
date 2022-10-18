 package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NoProperDataException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.models.Order;
import com.example.demo.services.SequenceGeneratorService;
import com.example.demo.util.FiegnClientUtilOrder;

@RestController
@RequestMapping("/api/v3")
public class FeignControllerOrder {
	
	@Autowired
	private FiegnClientUtilOrder feignclientutilorder;

	@Autowired
	private SequenceGeneratorService service;
	
	@GetMapping("/allorders") 
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Order>> getAllOrders(@RequestHeader("Authorization") String token) throws OrderNotFoundException {
	{
		
		return feignclientutilorder.getAllOrders(token);
		
	}
	
	}
	
@PostMapping("/addOrders")
	@PreAuthorize("hasRole('USER') ")
	public ResponseEntity<Order> addOrders(@RequestBody Order order) throws NoProperDataException {
	
		order.setBookingOrderId(service.getSequenceNumberForOrder(Order.SEQUENCE_NAME));
		return feignclientutilorder.addOrders(order);
	}



	@DeleteMapping(path="/orders/{id}")
	@PreAuthorize("hasRole('ADMIN') ")
	public ResponseEntity<String> deleteOrder(@RequestHeader("Authorization") String token,@PathVariable int id) throws OrderNotFoundException {
			return feignclientutilorder.deleteOrder(token,id);
	}

}

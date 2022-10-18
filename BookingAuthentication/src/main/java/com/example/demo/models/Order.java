package com.example.demo.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Document(collection="order")
	public class Order {
		
		public static final String SEQUENCE_NAME = "order_sequence";
		@Id
	    private int bookingOrderId;
	   private LocalDate orderDate;
	    private String transactionMode;
		private int quantity;
		double totalCost;
		
}

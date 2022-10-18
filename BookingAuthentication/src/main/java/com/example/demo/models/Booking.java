package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="booking_details")
public class Booking {
		
		public static final String SEQUENCE_NAME = "booking_sequence";
	@Id
	private	int bookingId;
	private	String trainName;
	private String source;
	private String destination;
	private int seatNum;
	private	double cost;
	private String bookingDate;
	public int getBookingId() {
		return bookingId;
	}
	
}

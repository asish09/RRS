package com.example.demo.model;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection="booking_details")
public class Booking {
	public static final String SEQUENCE_NAME = "booking_sequence";
	@Id
	private	int bookingId;
	@NotNull
	private	String trainName;
	@NotBlank(message="source")
	@Size(max=20)
	private String source;
	@NotBlank(message="destination")
	@Size(max=40)
	private String destination;
	@NotBlank(message="NumberOfSeats")
	@Size(min=30)
	private int seatNum;
	@NotBlank(message="cost")
	@Size(min=10)
	private	double cost;
	@Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Only digits are allowed for date in the format dd-mm-yyyy")
	private String bookingDate;



	
	


}




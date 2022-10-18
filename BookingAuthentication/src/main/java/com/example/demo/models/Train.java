package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="train_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {
	public static final String SEQUENCE_NAME = "train_sequence";
	@Id
	Integer trainId;
	
	String trainName;
	float trainTime;
	String destination;
	String trainAvailable;
	String trainNumber;
	int trainCost;
}
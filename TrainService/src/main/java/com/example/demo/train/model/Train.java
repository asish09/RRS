package com.example.demo.train.model;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Document(collection="train_details")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor


	public class Train {
		public static final String SEQUENCE_NAME = "train_sequence";
		
		@Id int trainId;
		@NotNull(message="enter trainTime")
		float trainTime;
		@NotBlank(message="enter trainName")
		String trainName;
		@NotBlank(message="enter trainAvailable")
		String trainAvailable;
		@NotNull(message="enter trainNumber")
		String trainNumber;
		@NotNull(message="enter trainCost")
		int trainCost;
		
			
		}

		
		
	






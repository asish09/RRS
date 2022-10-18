package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "db_sequence_train")
@Data
@AllArgsConstructor
@NoArgsConstructor
	public class DbSequenceTrain {
	    @Id
	    private String  id;
	    private int seq;
		
}


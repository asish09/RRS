package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class TrainNotFoundException extends Exception{
    
	public TrainNotFoundException(String message) {
		super(message);
	}
}
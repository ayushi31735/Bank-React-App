package com.techlabs.bank.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserNotFoundException extends RuntimeException{
		
	private String message;
	private HttpStatus status;
	
	
	@Override
	public String getMessage() {
		return message;
	}
	
}

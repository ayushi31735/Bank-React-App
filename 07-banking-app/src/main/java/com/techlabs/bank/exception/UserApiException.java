package com.techlabs.bank.exception;

import org.springframework.http.HttpStatus;

public class UserApiException extends RuntimeException{
	
	private String message;
	private HttpStatus status;
	
	public UserApiException(HttpStatus status,String message) {
		super();
		this.message = message;
		this.status = status;
	}

	@Override
	public String getMessage() {
		return "UserApiException [message=" + message + ", status=" + status + "]";
	}
	
}

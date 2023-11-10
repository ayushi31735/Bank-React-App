package com.techlabs.bank.exception;

public class InvalidCredentialsException extends RuntimeException{
	
	public InvalidCredentialsException()
	{
		
	}
	
	public String getMessage()
	{
		return "Invalid Credentials";
	}
	
}

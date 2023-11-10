package com.techlabs.bank.exception;

public class UsernameNotAvailableException extends RuntimeException{
	
	public UsernameNotAvailableException()
	{
		
	}
	
	public String getMessage()
	{
		return "Username already exists... Please Enter another username";
	}
	
}

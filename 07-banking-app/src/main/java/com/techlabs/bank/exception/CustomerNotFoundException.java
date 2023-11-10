package com.techlabs.bank.exception;

public class CustomerNotFoundException extends RuntimeException{
	
	public CustomerNotFoundException()
	{
		
	}
	
	public String getMessage()
	{
		return "Customer Not Found";
	}
	
}

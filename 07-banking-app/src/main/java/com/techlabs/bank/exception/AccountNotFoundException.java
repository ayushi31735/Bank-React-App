package com.techlabs.bank.exception;

public class AccountNotFoundException  extends RuntimeException{
	
	public AccountNotFoundException()
	{
		
	}
	
	public String getMessage()
	{
		return "Account Not Found";
	}
	
}

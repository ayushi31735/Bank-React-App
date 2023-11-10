package com.techlabs.bank.exception;

public class BankAlreadyExistException extends RuntimeException{
	
	public BankAlreadyExistException()
	{
		
	}
	
	public String getMessage()
	{
		return "Bank Already Exists";
	}
	
}

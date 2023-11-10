package com.techlabs.bank.exception;

public class CannotInitiateTransactionException extends RuntimeException{
	
	public CannotInitiateTransactionException()
	{
		
	}
	
	public String getMessage()
	{
		return "You cannot transfer on same account";
	}
	
}

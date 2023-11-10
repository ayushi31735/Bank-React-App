package com.techlabs.bank.exception;

public class IllegalTransactionException extends RuntimeException{
	
	public IllegalTransactionException()
	{
		
	}
	
	public String getMessage()
	{
		return "Illegal Transaction...";
	}
	
}

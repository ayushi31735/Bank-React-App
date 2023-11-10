package com.techlabs.bank.exception;

public class NoDataFoundException extends RuntimeException{
	
	public NoDataFoundException()
	{
		
	}
	
	public String getMessage()
	{
		return "No Data Found";
	}
	
}

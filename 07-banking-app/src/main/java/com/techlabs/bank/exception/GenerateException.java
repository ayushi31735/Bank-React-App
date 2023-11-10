package com.techlabs.bank.exception;

public class GenerateException extends RuntimeException{
	
	public GenerateException()
	{
		
	}
	
	public String getMessage()
	{
		return "Something went wrong.. Please try again..";
	}
	
}

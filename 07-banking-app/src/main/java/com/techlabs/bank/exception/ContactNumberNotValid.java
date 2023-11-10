package com.techlabs.bank.exception;

public class ContactNumberNotValid extends RuntimeException{
	
	public ContactNumberNotValid()
	{
		
	}
	
	public String getMessage()
	{
		return "Please Enter a valid contact number";
	}
	
}

package com.techlabs.bank.exception;

public class RoleNotFoundException extends RuntimeException{
	
	public RoleNotFoundException()
	{
		
	}
	
	public String getMessage()
	{
		return "Role Not Found";
	}
	
}

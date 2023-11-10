package com.techlabs.bank.service;

import com.techlabs.bank.dto.LoginDto;
import com.techlabs.bank.dto.RegisterDto;

public interface AuthService {
	
	String login(LoginDto loginDto);
	String registerUser(RegisterDto registerDto);
	String registerAdmin(RegisterDto registerDto);
	
}

package com.techlabs.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRegisterationDto {
	
	private String firstname;
	private String lastname;
	private long contact;
	private String email;
	private int userid;
	
}

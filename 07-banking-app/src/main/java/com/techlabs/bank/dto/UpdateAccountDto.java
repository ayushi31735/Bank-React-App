package com.techlabs.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateAccountDto {
	
	private int accountnumber;
	private double balance;
	private int bankid;
	private boolean active;
	
}

package com.techlabs.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountWithBankDto {

	private int accountnumber;
	private String firstname;
	private String lastname;
	private double balance;
	private String bankname;
	private String branch;
	private String ifsccode;
	
}

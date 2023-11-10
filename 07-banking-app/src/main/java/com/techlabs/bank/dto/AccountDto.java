package com.techlabs.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDto {
	
	private int accountnumber;
	private double balance;
	
	
	@Override
	public String toString() {
		return "AccountDto [accountnumber=" + accountnumber + ", balance=" + balance + "]";
	}

	
}

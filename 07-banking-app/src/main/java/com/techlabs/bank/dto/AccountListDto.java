package com.techlabs.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountListDto {
	
	private int accountnumber;
	private double balance;
	private String bankname;
	private String branch;
	private String ifsccode;
	
	@Override
	public String toString() {
		return "AccountListDto [accountnumber=" + accountnumber + ", balance=" + balance + ", bankname=" + bankname
				+ ", branch=" + branch + ", ifsccode=" + ifsccode + "]";
	}
	
	
}

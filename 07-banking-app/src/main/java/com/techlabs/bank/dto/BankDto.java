package com.techlabs.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDto {
	
	private int bankid;
	private String bankName;
	private String abbrevation;
	private String branch;
	private String ifsc;
	
}

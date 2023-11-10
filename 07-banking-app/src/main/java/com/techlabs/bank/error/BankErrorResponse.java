package com.techlabs.bank.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BankErrorResponse {
	
	private int status;
	private String message;
	private long timestamp;
	
}

package com.techlabs.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferTransactionDto {
	
	private int toaccount;
	private int fromaccount;
	private double amount;
	
}

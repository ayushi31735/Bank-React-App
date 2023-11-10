package com.techlabs.bank.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDto {
	
	private String transactionType;
	private double amount;
	private int fromAccount;
	private int toAccount;
	private Date transactionDate;
	private double currentBalance;
	
}

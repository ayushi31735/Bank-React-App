package com.techlabs.bank.service;

import java.util.List;

import com.techlabs.bank.dto.CreditTransactionDto;
import com.techlabs.bank.dto.DebitTransactionDto;
import com.techlabs.bank.dto.TransferTransactionDto;
import com.techlabs.bank.entity.Transaction;

public interface TransactionService {
	
	List<Transaction> getAllTransactions();
	List<Transaction> getTransactionByAccount(int accountnumber);
	Transaction debitAmount(DebitTransactionDto transactiondto);
	Transaction creditAmount(CreditTransactionDto transactiondto);
//	List<Transaction> transferAmount(TransferTransactionDto transactiondto);
	
}

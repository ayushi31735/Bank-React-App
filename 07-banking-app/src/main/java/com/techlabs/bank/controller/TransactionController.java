package com.techlabs.bank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.CreditTransactionDto;
import com.techlabs.bank.dto.DebitTransactionDto;
import com.techlabs.bank.dto.TransferTransactionDto;
import com.techlabs.bank.entity.Transaction;
import com.techlabs.bank.exception.CannotInitiateTransactionException;
import com.techlabs.bank.repository.TransactionRepository;
import com.techlabs.bank.service.TransactionService;

@RestController
@RequestMapping("/bankapp")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
//	@PreAuthorize("hasRole('USER')")
	@PostMapping("/transactions/credit") 
	public ResponseEntity<Transaction> creditAmount(@RequestBody CreditTransactionDto transactiondto)
	{
		Transaction transaction = transactionService.creditAmount(transactiondto);
		return ResponseEntity.ok(transaction);
	}
	
//	@PreAuthorize("hasRole('USER')")
	@PostMapping("/transactions/debit") 
	public ResponseEntity<Transaction> debitAmount(@RequestBody DebitTransactionDto transactiondto)
	{
		Transaction transaction = transactionService.debitAmount(transactiondto);
		return ResponseEntity.ok(transaction);
	}
	
//	@PreAuthorize("hasRole('USER')")
	@PostMapping("/transactions/transfer") 
	public List<Transaction> transferAmount(@RequestBody TransferTransactionDto transactiondto)
	{
		if(transactiondto.getFromaccount() == transactiondto.getToaccount())
		{
			throw new CannotInitiateTransactionException();
		}
		DebitTransactionDto debit = new DebitTransactionDto();
		debit.setAmount(transactiondto.getAmount());
		debit.setFromaccount(transactiondto.getFromaccount());
		Transaction debitTransaction = transactionService.debitAmount(debit);
		debitTransaction.setToAccount(transactiondto.getToaccount());
		
		CreditTransactionDto credit = new CreditTransactionDto();
		credit.setAmount(transactiondto.getAmount());
		credit.setToaccount(transactiondto.getToaccount());
		Transaction creditTransaction = transactionService.creditAmount(credit);
		System.out.println(transactiondto.getFromaccount());
		creditTransaction.setFromAccount(transactiondto.getFromaccount());
		System.out.println(creditTransaction.getFromAccount());
		
		List<Transaction> transactions = new ArrayList<Transaction>();	
		transactions.add(debitTransaction);
		transactions.add(creditTransaction);
		transactionRepository.saveAll(transactions);
		return transactions;
		
	}
	
//	@PreAuthorize("hasRole('USER')")
	@GetMapping("/transactionsbyaccount")
	public List<Transaction> getTransactionByAccount(@RequestParam int accountnumber)
	{
		List<Transaction> transactions = transactionService.getTransactionByAccount(accountnumber);
		return transactions;
	} 
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/transactions")
	public List<Transaction> getAllTransactions()
	{
		List<Transaction> transactions = transactionService.getAllTransactions();
		return transactions;
	} 
	
}

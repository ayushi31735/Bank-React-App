package com.techlabs.bank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.CreditTransactionDto;
import com.techlabs.bank.dto.DebitTransactionDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Transaction;
import com.techlabs.bank.exception.AccountNotFoundException;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;

//	public List<Transaction> transferAmount(TransferTransactionDto transactiondto) {
//		
//		Account toAccount = accountRepository.findByAccountnumberAndActive(transactiondto.getToaccount(), true);
//		if(toAccount == null)
//		{
//			throw new AccountNotFoundException();
//		}
//		
//		Account fromAccount = accountRepository.findByAccountnumberAndActive(transactiondto.getFromaccount(), true);
//		if(fromAccount == null)
//		{
//			throw new AccountNotFoundException();
//		}
//	
//		List<Transaction> transactions = new ArrayList<Transaction>();
//		Transaction debitTransaction = debitAmount(fromaccount, transaction);
//		System.out.println(debitTransaction.getTransactionid());
//		transactions.add(debitTransaction);
//		Transaction creditTransaction = creditAmount(toaccount, debitTransaction);
//		creditTransaction.setTransactionid(debitTransaction.getTransactionid()+1);
//		System.out.println(creditTransaction.getTransactionid());
//		transactions.add(creditTransaction);
//		transactionRepository.save(debitTransaction);
//		transactionRepository.save(creditTransaction);
//		
//		for(Transaction transaction1 : transactions)
//		{
//			System.out.println(transaction1);
//		}
//		transactionRepository.saveAll(transactions);
//		return transactions;
//	}

	public Transaction debitAmount(DebitTransactionDto transactiondto) {
		Account account = accountRepository.findByAccountnumberAndActive(transactiondto.getFromaccount(), true);
		if(account == null)
		{
			throw new AccountNotFoundException();
		}
		
		Transaction transaction = new Transaction();
		
		double balance = 0;
		balance = account.getBalance();
		double tempBalance = balance;
		transaction.setFromAccount(transactiondto.getFromaccount());
		tempBalance = tempBalance-transactiondto.getAmount();
		account.setBalance(tempBalance);
		updateAccount(account);
		double currentBalance = balance - transactiondto.getAmount();
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDate(new Date());
		transaction.setTransactionType("debit");
		transaction.setAccount(account);
		transaction.setAmount(transactiondto.getAmount());
		transactionRepository.save(transaction);
		return transaction;
	}

	public Transaction creditAmount(CreditTransactionDto transactiondto) {
		
		Account account = accountRepository.findByAccountnumberAndActive(transactiondto.getToaccount(), true);
		if(account == null)
		{
			throw new AccountNotFoundException();
		}
		
		Transaction transaction = new Transaction();
		
		double balance = 0;
		balance = account.getBalance();
		double tempBalance = balance;
		transaction.setToAccount(transactiondto.getToaccount());
		tempBalance = tempBalance+transactiondto.getAmount();
		account.setBalance(tempBalance);
		updateAccount(account);
		double currentBalance = balance + transactiondto.getAmount();
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDate(new Date());
		transaction.setTransactionType("credit");
		transaction.setAccount(account);
		transaction.setAmount(transactiondto.getAmount());
		transactionRepository.save(transaction);
		return transaction;
	}
	
	public Account updateAccount(Account account) {
		
		return accountRepository.save(account);

	}

	@Override
	public List<Transaction> getTransactionByAccount(int accountnumber) {
		List<Transaction> transactions = transactionRepository.findAll();
		List<Transaction> accountTransaction = new ArrayList<Transaction>();
		for(Transaction transaction : transactions)
		{
			if(transaction.getAccount().getAccountnumber() == accountnumber)
			{
				accountTransaction.add(transaction);
			}
		}
		return accountTransaction;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactions = transactionRepository.findAll();
		return transactions;
	}

}

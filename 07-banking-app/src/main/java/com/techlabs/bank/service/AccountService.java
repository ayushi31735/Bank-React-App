package com.techlabs.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.dto.AccountListDto;
import com.techlabs.bank.dto.AccountWithBankDto;
import com.techlabs.bank.dto.CreateAccountDto;
import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.dto.UpdateAccountDto;
import com.techlabs.bank.entity.Account;

public interface AccountService {
	
	Account addAccount(CreateAccountDto accountdtoO);
	List<Account> getAllAccounts();
	Page<Account> getAccountsPageWise(int pageumber,int pagesize);
	void removeAccount(int accountnumber);
	void updateAccount(Account account);
	List<AccountWithBankDto> getAllAccountsDtos();
	AccountDto getAccountByAccountNumber(int accountnumber);
	List<AccountDto> getActiveAccounts();
	AccountDto getActiveAccountByAccountNumber(int accountnumber);
	Page<AccountWithBankDto> getAllAccounts(Pageable pageable);
	List<AccountListDto> getAccount(int customerid);
	List<TransactionDto> getTransaction(int accountnumber);
	boolean updateActiveAccount(UpdateAccountDto accountdto);
	
}

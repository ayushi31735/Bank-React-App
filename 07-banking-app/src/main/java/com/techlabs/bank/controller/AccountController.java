package com.techlabs.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.dto.AccountListDto;
import com.techlabs.bank.dto.AccountWithBankDto;
import com.techlabs.bank.dto.CreateAccountDto;
import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.dto.UpdateAccountDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.service.AccountService;

@RestController
@RequestMapping("/bankapp")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/accounts") 
	 public ResponseEntity<Account> addAccount(@RequestBody CreateAccountDto accountdto) 
	 { 
		Account account  = accountService.addAccount(accountdto);
		return ResponseEntity.ok(account); 
	 } 
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/accounts")
	public List<Account> getAllAccounts()
	{
		return accountService.getAllAccounts();
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/accounts")
	public void removeAccount(@RequestParam int accountnumber)
	{
		accountService.removeAccount(accountnumber);
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/accounts")
	public void updateAccount(@RequestBody Account account)
	{
		accountService.updateAccount(account);
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/accounts/accountsdto")
	public List<AccountWithBankDto> getAllAccountsDto()
	{
		List<AccountWithBankDto> accounts = accountService.getAllAccountsDtos();
		return accounts;
	} 
	
//	@PreAuthorize("hasRole('USER')")
	@GetMapping("/accounts/byaccountnumber")
	public AccountDto getAccountById(@RequestParam int accountnumber)
	{
		AccountDto accounts = accountService.getAccountByAccountNumber(accountnumber);
		return accounts;
	} 
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/accounts/activeaccounts")
	public List<AccountDto> getActiveAccounts()
	{
		List<AccountDto> accounts = accountService.getActiveAccounts();
		return accounts;
	} 
	
	@GetMapping("/accounts/byactiveaccountnumber")
	public AccountDto getActiveAccountByAccountNumber(@RequestParam int accountnumber)
	{
		AccountDto accounts = accountService.getActiveAccountByAccountNumber(accountnumber);
		return accounts;
	} 
	
	@GetMapping("/accounts/dto")
	public ResponseEntity<Page<AccountWithBankDto>> getAllAccounts(@RequestParam int pageNumber,@RequestParam int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		Page<AccountWithBankDto> accounts = accountService.getAllAccounts(pageable);
		System.out.println("total records are "+accounts.getTotalElements());
		HttpHeaders headers = new HttpHeaders();
	    headers.add("X-Total-Count", String.valueOf(accounts.getTotalElements()));
	    return ResponseEntity.ok().headers(headers).body(accounts);
			
	}
	
	@GetMapping("/accounts/ofcustomer")
	public ResponseEntity<List<AccountListDto>> getAccountByCustomer(@RequestParam int customerid)
	{
		List<AccountListDto> account = accountService.getAccount(customerid);
		return ResponseEntity.ok(account);
	}
	
	@GetMapping("/accounts/transactions")
	public ResponseEntity<List<TransactionDto>> getTransactions(@RequestParam int accountnumber)
	{
		List<TransactionDto> transactions = accountService.getTransaction(accountnumber);
		return ResponseEntity.ok(transactions);
	} 
	
	@PutMapping("/accounts/bank")
	public ResponseEntity<String> updateAccount(@RequestBody UpdateAccountDto accountdto)
	{
		boolean check = accountService.updateActiveAccount(accountdto);
		String checkMsg = null;
		if(check == true)
		{
			checkMsg = "Account Updated successfully";
		}
		else
		{
			checkMsg = "Something went wrong!! Please Try Again";
		}
		return ResponseEntity.ok().body(checkMsg);
	}
	
}

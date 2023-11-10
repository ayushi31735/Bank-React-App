package com.techlabs.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.dto.AccountListDto;
import com.techlabs.bank.dto.AccountWithBankDto;
import com.techlabs.bank.dto.CreateAccountDto;
import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.dto.UpdateAccountDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Bank;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.Transaction;
import com.techlabs.bank.exception.AccountNotFoundException;
import com.techlabs.bank.exception.BankNotFoundException;
import com.techlabs.bank.exception.CustomerNotFoundException;
import com.techlabs.bank.mapper.AccountMapper;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.BankRepository;
import com.techlabs.bank.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Account addAccount(CreateAccountDto accountdto) {
		Bank bank = bankRepository.findByBankidAndActive(accountdto.getBankid(),true);
		if(bank == null)
		{
			throw new BankNotFoundException("Bank Not Found",HttpStatus.NOT_FOUND);
		}
		Customer customer = customerRepository.findByCustomeridAndActive(accountdto.getCustomerid(), true);
		if(customer == null)
		{
			throw new CustomerNotFoundException();
		}
		
		Account account = new Account();
		account.setBalance(accountdto.getBalance());
		account.setActive(true);
		account.setBank(bank);
		account.setCustomer(customer);
		
		
		return accountRepository.save(account);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Page<Account> getAccountsPageWise(int pageumber, int pagesize) {
		PageRequest pageable = PageRequest.of(pageumber, pagesize);
		return accountRepository.findAll(pageable);
	}

	@Override
	public void removeAccount(int accountnumber) {
		List<Account> accounts = accountRepository.findByActive(true);
		for(Account account : accounts)
		{
			if(account.getAccountnumber() == accountnumber)
			{
				account.setActive(false);
				updateAccount(account);
			}
		}
	}

	@Override
	public void updateAccount(Account account) {
		accountRepository.save(account);
	}

	@Override
	public List<AccountWithBankDto> getAllAccountsDtos() {
		List<Account> accounts = accountRepository.findByActive(true);
		if(accounts == null)
		{
			throw new AccountNotFoundException();
		}
		AccountWithBankDto accountDto = null;
		List<AccountWithBankDto> accountdtos = new ArrayList<AccountWithBankDto>();
		for(Account account : accounts)
		{
			accountDto = new AccountWithBankDto();
			accountDto.setAccountnumber(account.getAccountnumber());
			accountDto.setBalance(account.getBalance());
			accountDto.setBankname(account.getBank().getBankname());
			accountDto.setBranch(account.getBank().getBranch());
			accountDto.setIfsccode(account.getBank().getIfscCode());
			accountDto.setFirstname(account.getCustomer().getFirstname());
			accountDto.setLastname(account.getCustomer().getLastname());
			accountdtos.add(accountDto);
		}
		return accountdtos;
	}

	@Override
	public AccountDto getAccountByAccountNumber(int accountnumber) {
		Optional<Account> account = accountRepository.findById(accountnumber);
		if(account.isEmpty())
		{
			throw new AccountNotFoundException();
		}
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountnumber(account.get().getAccountnumber());
		accountDto.setBalance(account.get().getBalance());
		return accountDto;
	}

	@Override
	public List<AccountDto> getActiveAccounts() {
		List<Account> accounts = accountRepository.findByActive(true);
		if(accounts.isEmpty())
		{
			throw new AccountNotFoundException();
		}
		AccountDto accountDto = null;
		List<AccountDto> accountdto = new ArrayList<AccountDto>();
		for(Account account : accounts)
		{
			accountDto = new AccountDto();
			accountDto.setAccountnumber(account.getAccountnumber());
			accountDto.setBalance(account.getBalance());
			accountdto.add(accountDto);
		}
		return accountdto;
	}

	@Override
	public AccountDto getActiveAccountByAccountNumber(int accountnumber) {
		Account account = accountRepository.findByAccountnumberAndActive(accountnumber, true);
		if(account == null)
		{
			throw new AccountNotFoundException();
		}
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountnumber(account.getAccountnumber());
		accountDto.setBalance(account.getBalance());
		return accountDto;
	}

	@Override
	public Page<AccountWithBankDto> getAllAccounts(Pageable pageable) {
		Page<Account> accounts = accountRepository.findByActiveTrue(pageable);
	    Page<AccountWithBankDto> accountDto = accounts.map(account -> AccountMapper.AccountToAccountWithBankDto(account));

	    return accountDto;
	}

	@Override
	public List<AccountListDto> getAccount(int customerid) {
		List<Account> accounts = accountRepository.findAll();
		List<AccountListDto> accountlist = new ArrayList<AccountListDto>();
		AccountListDto account = null;
		for(Account tempaccount:accounts)
		{
			if(tempaccount.getCustomer().getCustomerid() == customerid)
			{
				account = new AccountListDto();
				account.setAccountnumber(tempaccount.getAccountnumber());
				account.setBalance(tempaccount.getBalance());
				account.setBankname(tempaccount.getBank().getBankname());
				account.setBranch(tempaccount.getBank().getBranch());
				account.setIfsccode(tempaccount.getBank().getIfscCode());
				accountlist.add(account);
			}
			
		}
		return accountlist;
	}

	@Override
	public List<TransactionDto> getTransaction(int accountnumber) {
		Optional<Account> account = accountRepository.findById(accountnumber);
		List<Transaction> transactions = account.get().getTransactions();
		List<TransactionDto> transactiondto = new ArrayList<TransactionDto>();
		TransactionDto temptransaction = null;
		for(Transaction transaction:transactions)
		{
			temptransaction = new TransactionDto();
			temptransaction.setTransactionType(transaction.getTransactionType());
			temptransaction.setAmount(transaction.getAmount());
			temptransaction.setFromAccount(transaction.getFromAccount());
			temptransaction.setToAccount(transaction.getToAccount());
			temptransaction.setTransactionDate(transaction.getTransactionDate());
			temptransaction.setCurrentBalance(transaction.getCurrentBalance());
			transactiondto.add(temptransaction);
		}
		return transactiondto;
	}

	@Override
	public boolean updateActiveAccount(UpdateAccountDto accountdto) {
		Account account = accountRepository.findById(accountdto.getAccountnumber()).get();
		Bank bank = bankRepository.findById(accountdto.getBankid()).get();
		account.setBank(bank);
		account.setActive(true);
		accountRepository.save(account);
		return true;
	}

}

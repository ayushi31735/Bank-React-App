package com.techlabs.bank.mapper;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.dto.AccountListDto;
import com.techlabs.bank.dto.AccountWithBankDto;
import com.techlabs.bank.entity.Account;

public class AccountMapper {
	
	public static AccountDto AccountToAccountDto(Account account) {
		
        AccountDto accountdto = new AccountDto();
        accountdto.setAccountnumber(account.getAccountnumber());
        accountdto.setBalance(account.getBalance());
       
        return accountdto;
    }
	
	public static AccountWithBankDto AccountToAccountWithBankDto(Account account)
	{
		AccountWithBankDto accountDto = new AccountWithBankDto();
		accountDto.setAccountnumber(account.getAccountnumber());
		accountDto.setBalance(account.getBalance());
		accountDto.setBankname(account.getBank().getBankname());
		accountDto.setBranch(account.getBank().getBranch());
		accountDto.setIfsccode(account.getBank().getIfscCode());
		accountDto.setFirstname(account.getCustomer().getFirstname());
		accountDto.setLastname(account.getCustomer().getLastname());
		
		return accountDto;
	}

}

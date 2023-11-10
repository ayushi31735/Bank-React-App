package com.techlabs.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techlabs.bank.dto.BankDto;
import com.techlabs.bank.entity.Bank;

public interface BankService {
	
	Bank addBank(Bank bank);
	Page<BankDto> getAllBanks(Pageable pageable);
	Page<Bank> getBanksPageWise(int pageumber,int pagesize);
	boolean removeBank(int bankid);
	boolean updateBank(Bank bank);
//	Bank getBankByIfscCode(String ifsc);
	List<Bank> getBankByAbbrevation(String abbrevation);
	List<Bank> getBankByAbbrevationAndActive(String abbrevation);
	List<Bank> getBankByBranch(String branch);
	List<Bank> getBankByActiveBranch(String branch);
	List<Bank> getActiveBanks();
//	Page<BankDto> getBanksPageWiseDto(int pageumber,int pagesize);
	List<BankDto> getBanksByBankname(String bankname);
	int getBankid(String bankname,String branch);
	
}

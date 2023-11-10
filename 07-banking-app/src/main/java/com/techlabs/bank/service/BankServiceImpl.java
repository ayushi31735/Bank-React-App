package com.techlabs.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.BankDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Bank;
import com.techlabs.bank.exception.BankAlreadyExistException;
import com.techlabs.bank.exception.BankNotFoundException;
import com.techlabs.bank.exception.GenerateException;
import com.techlabs.bank.mapper.BankMapper;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.BankRepository;

@Service
public class BankServiceImpl implements BankService{

	@Autowired
	private BankRepository bankRepositry;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Bank addBank(Bank bank) {
		
		validateBank(bank);
		String ifsc = generateIfsc(bank);
		boolean check = validateIfsc(ifsc);
		if(check == true)
			bank.setIfscCode(ifsc);
		else
			throw new GenerateException();
		
		bank.setActive(true);
		return bankRepositry.save(bank);
	}

	public void validateBank(Bank bank) {
		Bank bankcheck = bankRepositry.findByBanknameAndBranch(bank.getBankname(), bank.getBranch());
		if(bankcheck != null)
		{
			throw new BankAlreadyExistException();
		}
	}

	public boolean validateIfsc(String ifsc) {
		Bank bank = bankRepositry.findByIfscCode(ifsc);
		if(bank == null)
		{
			return true;
		}
		return false;
	}

	public String generateIfsc(Bank bank) {
		String abbrevation = bank.getAbbrevation();
		int number = genarateIfscNumber();
		String ifsc = abbrevation+number;
		return ifsc;
	}
	
	private int genarateIfscNumber() {
		Random random = new Random();
		int low = 10000;
		int high = 1000000;
		int number = random.nextInt(high-low) + low;
		return number;
	}

	@Override
	public Page<BankDto> getAllBanks(Pageable pageable) {
		Page<Bank> banks = bankRepositry.findByActiveTrue(pageable);
	    Page<BankDto> bankList = banks.map(bank -> BankMapper.BanktobankDto(bank));

//	    if (bankList.isEmpty()) {
//	        throw new DuplicateUsernameException("No bank found");
//	    }

	    return bankList;
	}

	@Override
	public Page<Bank> getBanksPageWise(int pagenumber, int pagesize) {
		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		return bankRepositry.findAll(pageable);
	}

	@Override
	public boolean removeBank(int bankid) {
		Bank bank = bankRepositry.findByBankidAndActive(bankid, true);
		if(bank == null)
		{
			throw new BankNotFoundException("Bank Not Found",HttpStatus.NOT_FOUND);
		}
		List<Account> accounts = accountRepository.findByActive(true);
		for(Account account : accounts)
		{
			if(account.getBank().getBankid() == bankid)
			{
				account.setActive(false);
				updateAccount(account);
			}
		}
		bank.setActive(false);
		updateBank(bank);
		return true;
	}

	public void updateAccount(Account account) {
		accountRepository.save(account);
	}
	
	@Override
	public boolean updateBank(Bank bank) {
		bankRepositry.save(bank);
		return true;
		
	}

//	@Override
//	public Bank getBankByIfscCode(String ifsc) {
//		Bank bank = bankRepositry.findByIfscCode(ifsc);
//		System.out.println(bank);
//		List<Bank> testBank = getAllBanks();
//		for(Bank bank1 : testBank)
//		{
//			System.out.println(bank1);
//			System.out.println(bank1.getIfscCode().equals(ifsc));
//			if(bank1.getIfscCode().equalsIgnoreCase(ifsc))
//			{
//				return bank1;
//			}
//		}
//		if(bank == null)
//		{
//			throw new BankNotFoundException();
//		}
//		return bank;
//	}

	@Override
	public List<Bank> getBankByAbbrevation(String abbrevation) {
		List<Bank> banks = bankRepositry.findByAbbrevation(abbrevation);
		if(banks == null)
		{
			throw new BankNotFoundException("Bank Not Found",HttpStatus.NOT_FOUND);
		}
		return banks;
	}
	
	@Override
	public List<Bank> getBankByAbbrevationAndActive(String abbrevation) {
		List<Bank> banks = bankRepositry.findByAbbrevationAndActive(abbrevation, true);
		if(banks == null)
		{
			throw new BankNotFoundException("Bank Not Found",HttpStatus.NOT_FOUND);
		}
		return banks;
	}

	@Override
	public List<Bank> getBankByBranch(String branch) {
		List<Bank> banks = bankRepositry.findByBranch(branch);
		if(banks == null)
		{
			throw new BankNotFoundException("Bank Not Found",HttpStatus.NOT_FOUND);
		}
		return banks;
	}

	@Override
	public List<Bank> getBankByActiveBranch(String branch) {
		List<Bank> banks = bankRepositry.findByBranchAndActive(branch, true);
		if(banks == null)
		{
			throw new BankNotFoundException("Bank Not Found",HttpStatus.NOT_FOUND);
		}
		return banks;
	}

	@Override
	public List<Bank> getActiveBanks() {
		List<Bank> banks = bankRepositry.findByActive(true);
		if(banks == null)
		{
			throw new BankNotFoundException("Bank Not Found",HttpStatus.NOT_FOUND);
		}
		return banks;
	}
//
//	@Override
//	public Page<BankDto> getBanksPageWiseDto(int pageumber, int pagesize) {
//		Pageable pageable = PageRequest.of(pageumber, pagesize);
//		return bankDtoRepository.findAll(pageable);
//	}

	@Override
	public List<BankDto> getBanksByBankname(String bankname) {
		List<Bank> banks = bankRepositry.findByBankname(bankname);
		List<BankDto> bankdtos = new ArrayList<BankDto>();
		BankDto bankdto = null;
		for(Bank bank : banks)
		{
			bankdto = new BankDto();
			bankdto.setBankid(bank.getBankid());
			bankdto.setBankName(bank.getBankname());
			bankdto.setAbbrevation(bank.getAbbrevation());
			bankdto.setBranch(bank.getBranch());
			bankdto.setIfsc(bank.getIfscCode());
			bankdtos.add(bankdto);
		}
		return bankdtos; 
	}

	@Override
	public int getBankid(String bankname, String branch) {
		Bank bank = bankRepositry.findByBanknameAndBranch(bankname, branch);
		return bank.getBankid();
	}
	
	

}

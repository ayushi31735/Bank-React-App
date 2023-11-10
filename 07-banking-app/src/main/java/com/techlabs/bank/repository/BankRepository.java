package com.techlabs.bank.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.dto.BankDto;
import com.techlabs.bank.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{
	
	Bank findByBankid(int bankid);
	Bank findByBankidAndActive(int bankid,boolean active);
	Bank findByIfscCode(String ifsc);
	List<Bank> findByAbbrevation(String abbrevation);
	List<Bank> findByAbbrevationAndActive(String abbrevation,boolean active);
	List<Bank> findByBranch(String branch);
	List<Bank> findByBranchAndActive(String branch,boolean active);
	List<Bank> findByActive(boolean active);
	Bank findByBanknameAndBranch(String bankname,String branch);
	Page<Bank> findByActiveTrue(Pageable pageable);
	List<Bank> findByBankname(String bankname);
	
}

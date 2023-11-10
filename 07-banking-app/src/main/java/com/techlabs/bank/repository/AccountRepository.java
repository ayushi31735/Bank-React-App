package com.techlabs.bank.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
		
	List<Account> findByActive(boolean active);
	Account findByAccountnumberAndActive(int accountnumber,boolean active);
	Page<Account> findByActiveTrue(Pageable pageable);
	 
}

package com.techlabs.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	
	
}

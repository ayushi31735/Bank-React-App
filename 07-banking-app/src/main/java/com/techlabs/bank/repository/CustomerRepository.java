package com.techlabs.bank.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Customer findByCustomeridAndActive(int customerid,boolean active);
	List<Customer> findByActive(boolean active);
	List<Customer> findByFirstname(String firstname);
	Page<Customer> findByActiveTrue(Pageable pageable);
//	Customer findByUseridAndActive(int userid, boolean active);
	
}

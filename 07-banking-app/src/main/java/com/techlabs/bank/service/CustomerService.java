package com.techlabs.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techlabs.bank.dto.AccountListDto;
import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.CustomerRegisterationDto;
import com.techlabs.bank.dto.UpdateCustomerDto;
import com.techlabs.bank.entity.Customer;

public interface CustomerService {
	
	Customer addCustomer(CustomerRegisterationDto customer);
	List<Customer> getAllCustomers();
	Page<Customer> getCustomersPageWise(int pageumber,int pagesize);
	boolean removeCustomer(int customerid);
	void updateActiveCustomer(Customer customer);
	boolean updateCustomer(UpdateCustomerDto customerdto);
	List<CustomerDto> getAllCustomersDto();
	CustomerDto getCustomer(int customerid);
	List<CustomerDto> getActiveCustomers();
	List<CustomerDto> getCustomersByName(String firstname);
	CustomerDto getActiveCustomerById(int customerid);
	Page<CustomerDto> getCustomer(Pageable pageable);
	CustomerDto getCustomerByUser(int userid);
	List<AccountListDto> getAccount(int customerid);
	int getUserId(int customerid);
}

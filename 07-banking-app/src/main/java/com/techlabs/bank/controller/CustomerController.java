package com.techlabs.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.AccountListDto;
import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.CustomerRegisterationDto;
import com.techlabs.bank.dto.UpdateCustomerDto;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.exception.CustomerNotFoundException;
import com.techlabs.bank.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bankapp")
@RequiredArgsConstructor
@CrossOrigin(value="*")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/customers") 
	 public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRegisterationDto customer) 
	 { 
		Customer dbcustomer = customerService.addCustomer(customer);
		return ResponseEntity.ok(dbcustomer); 
	 } 
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customers")
	public ResponseEntity<Page<Customer>> getAllCustomers(@RequestParam Map<String,String> params)
	{
		Page<Customer> customerPage = null;
		customerPage = customerService.getCustomersPageWise(0, customerService.getAllCustomers().size());
		
		if(customerPage.getTotalElements() == 0)
		{
			throw new CustomerNotFoundException();
		}
		
		return new ResponseEntity<>(customerPage,HttpStatus.OK);
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/customers")
	public ResponseEntity<String> removeCustomer(@RequestParam int customerid)
	{
		boolean check = customerService.removeCustomer(customerid);
		String checkMsg = null;
		if(check==true)
		{
			checkMsg="Customer Deleted Successfully";
		}
		else
		{
			checkMsg="Something went wrong!! Please try again";
		}
		return ResponseEntity.ok().body(checkMsg);
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/customers")
	public ResponseEntity<String> updateCustomer(@RequestBody UpdateCustomerDto customer)
	{
		boolean check = customerService.updateCustomer(customer);
		String checkMsg = null;
		if(check == true)
		{
			checkMsg = "Customer Updated Successfully";
		}
		else
		{
			checkMsg = "Please Try Again";
		}
		return ResponseEntity.ok().body(checkMsg);
	}
	
//	@PreAuthorize("hasRole('USER')")
	@GetMapping("/customers/byid")
	public ResponseEntity<CustomerDto> getCustomers(@RequestParam int customerid)
	{
		return ResponseEntity.ok(customerService.getCustomer(customerid));
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customers/customersdtomap")
	public List<CustomerDto> getAllCustomers()
	{
		List<CustomerDto> customers = customerService.getAllCustomersDto();
		return customers;
	}
	
	@GetMapping("/customers/customersdto")
	public ResponseEntity<Page<CustomerDto>> getAllCustomers(@RequestParam int pageNumber,@RequestParam int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		Page<CustomerDto> customers = customerService.getCustomer(pageable);
		HttpHeaders headers = new HttpHeaders();
	    headers.add("X-Total-Count", String.valueOf(customers.getTotalElements()));
	    return ResponseEntity.ok().headers(headers).body(customers);
			
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customers/activecustomersdto")
	public List<CustomerDto> getActiveCustomers()
	{
		List<CustomerDto> customers = customerService.getActiveCustomers();
		return customers;
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customers/byname")
	public List<CustomerDto> getCustomersByFirstname(@RequestParam String name)
	{
		System.out.println(name);
		List<CustomerDto> customers = customerService.getCustomersByName(name);
		return customers;
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customers/activebyid")
	public CustomerDto getActiveCustomersById(@RequestParam int customerid)
	{
		CustomerDto customerDto = customerService.getActiveCustomerById(customerid);
		return customerDto;
	}
	
	@GetMapping("/customers/byuser")
	public CustomerDto getCustomerByUser(@RequestParam int userid)
	{
		System.out.println(userid);
		CustomerDto customerDto = customerService.getCustomerByUser(userid);
		return customerDto;
	}
	
	@GetMapping("/customers/accounts")
	public List<AccountListDto> getAccounts(@RequestParam int customerid)
	{
		List<AccountListDto> accounts = customerService.getAccount(customerid);
		return accounts;
	}
	
	@GetMapping("/customers/userid")
	public ResponseEntity<Integer> getUserId(@RequestParam int customerid)
	{
		int userid = customerService.getUserId(customerid);
		return ResponseEntity.ok().body(userid);
	}
}


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

import com.techlabs.bank.dto.AccountListDto;
import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.CustomerRegisterationDto;
import com.techlabs.bank.dto.UpdateCustomerDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.exception.ContactNumberNotValid;
import com.techlabs.bank.exception.CustomerNotFoundException;
import com.techlabs.bank.exception.UserNotFoundException;
import com.techlabs.bank.mapper.CustomerMapper;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.RoleRepository;
import com.techlabs.bank.repository.UserRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Customer addCustomer(CustomerRegisterationDto customerdto) {
		
//		Optional<User> user = userRepository.findById(userid);
//		if(user.isEmpty())
//		{
//			throw new UserNotFoundException();
//		}
		if(!userRepository.existsByUseridAndActive(customerdto.getUserid(),true))
		{
			throw new UserNotFoundException("User Not Found",HttpStatus.NOT_FOUND);
		}
		Optional<User> user = userRepository.findById(customerdto.getUserid());
		if(user.isEmpty())
		{
			throw new UserNotFoundException("User Not Found",HttpStatus.NOT_FOUND);
		}
		Customer customer = new Customer();
		customer.setFirstname(customerdto.getFirstname());
		customer.setLastname(customerdto.getLastname());
		customer.setContact(customerdto.getContact());
		customer.setEmail(customerdto.getEmail());
		customer.setUser(user.get());
		customer.setActive(true);
			
		return customerRepository.save(customer);
	}

	
	public void validateContact(long contact)
	{
		int count=0;
		long contactNumber = contact;
		while(contactNumber!=0)
		{
			count++;
			long digit = contactNumber%10;
			if(!(digit > 0 || digit < 9))
			{
				throw new ContactNumberNotValid();
			}
			contactNumber = contactNumber/10;
		}
		if(count < 10)
			throw new ContactNumberNotValid();
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Page<Customer> getCustomersPageWise(int pageumber, int pagesize) {
		Pageable pageable = PageRequest.of(pageumber, pagesize);
		return customerRepository.findAll(pageable);
	}

	@Override
	public boolean removeCustomer(int customerid) {
		
		Customer customer = customerRepository.findByCustomeridAndActive(customerid, true);
		
		if(customer == null)
		{
			throw new CustomerNotFoundException();
		}
		List<Account> accounts = accountRepository.findByActive(true);
		for(Account account : accounts)
		{
			if(account.getCustomer().getCustomerid() == customerid)
			{
				account.setActive(false);
				updateAccount(account);
			}
		}
		customer.setActive(false);
		updateActiveCustomer(customer);
		return true;
	}
	

	@Override
	public boolean updateCustomer(UpdateCustomerDto customerdto) {
		if(!userRepository.existsByUseridAndActive(customerdto.getUserid(),true))
		{
			throw new UserNotFoundException("User Not Found",HttpStatus.NOT_FOUND);
		}
		Optional<User> user = userRepository.findById(customerdto.getUserid());
		if(user.isEmpty())
		{
			throw new UserNotFoundException("User Not Found",HttpStatus.NOT_FOUND);
		}
		Customer customer = customerRepository.findById(customerdto.getCustomerid()).get();
		customer.setFirstname(customerdto.getFirstname());
		customer.setLastname(customerdto.getLastname());
		customer.setContact(customerdto.getContact());
		customer.setEmail(customerdto.getEmail());
//		customer.setUser(user.get());
		customer.setActive(true);
		customerRepository.save(customer);
		return true;
	}

	public void updateAccount(Account account) {
		accountRepository.save(account);
	}
	
	@Override
	public Page<CustomerDto> getCustomer(Pageable pageable) {
		Page<Customer> customers = customerRepository.findByActiveTrue(pageable);
	    Page<CustomerDto> customerdto = customers.map(customer -> CustomerMapper.CustomertoCustomerDto(customer));
	    
	    return customerdto;
	}
	
	@Override
	public List<CustomerDto> getAllCustomersDto() {
		List<Customer> dbCustomer = customerRepository.findAll();
		CustomerDto customerDto = null;
		List<CustomerDto> customerdto = new ArrayList<CustomerDto>();
		for(Customer customer : dbCustomer)
		{
			customerDto = new CustomerDto();
			customerDto.setCustomerid(customer.getCustomerid());
			customerDto.setFirstname(customer.getFirstname());
			customerDto.setLastname(customer.getLastname());
			customerDto.setContact(customer.getContact());
			customerDto.setEmail(customer.getEmail());
			customerdto.add(customerDto);
		}
		return customerdto;
	}

	@Override
	public CustomerDto getCustomer(int customerid) {
		Optional<Customer> customerdto = customerRepository.findById(customerid);
		if(customerdto.isEmpty())
		{
			throw new CustomerNotFoundException();
		}
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerid(customerdto.get().getCustomerid());
		customerDto.setFirstname(customerdto.get().getFirstname());
		customerDto.setLastname(customerdto.get().getLastname());
		customerDto.setContact(customerdto.get().getContact());
		customerDto.setEmail(customerdto.get().getEmail());
		return customerDto;
	}

	@Override
	public List<CustomerDto> getActiveCustomers() {
		List<Customer> customers = customerRepository.findByActive(true);
		if(customers == null)
		{
			throw new CustomerNotFoundException();
		}
		for(Customer customer : customers)
		{
			System.out.println(customer);
		}
		CustomerDto customerDto = null;
		List<CustomerDto> customerdto = new ArrayList<CustomerDto>();
		for(Customer customer : customers)
		{
			customerDto = new CustomerDto();
			customerDto.setFirstname(customer.getFirstname());
			customerDto.setLastname(customer.getLastname());
			customerDto.setEmail(customer.getEmail());
			customerDto.setCustomerid(customer.getCustomerid());
			customerDto.setContact(customer.getContact());
			customerdto.add(customerDto);
			}
		return customerdto;
	}

	@Override
	public List<CustomerDto> getCustomersByName(String firstname) {
		List<Customer> customers = customerRepository.findByFirstname(firstname);
		if(customers == null)
		{
			throw new CustomerNotFoundException();
		}
		CustomerDto customerDto = null;
		List<CustomerDto> customerdto = new ArrayList<CustomerDto>();
		for(Customer customer : customers)
		{
			customerDto = new CustomerDto();
			customerDto.setFirstname(customer.getFirstname());
			customerDto.setLastname(customer.getLastname());
			customer.setContact(customer.getContact());
			customerDto.setEmail(customer.getEmail());
			customerDto.setCustomerid(customer.getCustomerid());
			customerdto.add(customerDto);
			}
		return customerdto;
	}

	@Override
	public CustomerDto getActiveCustomerById(int customerid) {
		Customer customerdto = customerRepository.findByCustomeridAndActive(customerid, true);
		if(customerdto == null)
		{
			throw new CustomerNotFoundException();
		}
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerid(customerdto.getCustomerid());
		customerDto.setFirstname(customerdto.getFirstname());
		customerDto.setLastname(customerdto.getLastname());
		customerDto.setContact(customerdto.getContact());
		customerDto.setEmail(customerdto.getEmail());
		return customerDto;
	}

	@Override
	public List<AccountListDto> getAccount(int customerid) {
		Optional<Customer> customer = customerRepository.findById(customerid);
		List<Account> accounts = customer.get().getAccounts();
		List<AccountListDto> accountlist = new ArrayList<AccountListDto>();
		AccountListDto account = null;
		for(Account tempaccount:accounts)
		{
			account = new AccountListDto();
			account.setAccountnumber(tempaccount.getAccountnumber());
			account.setBalance(tempaccount.getBalance());
			account.setBankname(tempaccount.getBank().getBankname());
			account.setBranch(tempaccount.getBank().getBranch());
			account.setIfsccode(tempaccount.getBank().getIfscCode());
			accountlist.add(account);
		}
		return accountlist;
	}


	@Override
	public void updateActiveCustomer(Customer customer) {
		customerRepository.save(customer);
	}


	@Override
	public int getUserId(int customerid) {
		Optional<Customer> customer = customerRepository.findById(customerid);
		int userid = customer.get().getUser().getUserid();
		return userid;
	}


	@Override
	public CustomerDto getCustomerByUser(int userid) {
		List<Customer> customers = customerRepository.findAll();
		System.out.println(customers.size());
		CustomerDto customerdto = new CustomerDto();
		for(Customer customer:customers)
		{
//			System.out.println("abc");
			if(customer.getUser().getUserid() == userid)
			{
				customerdto.setCustomerid(customer.getCustomerid());
				customerdto.setFirstname(customer.getFirstname());
				customerdto.setLastname(customer.getLastname());
				customerdto.setEmail(customer.getEmail());
				customerdto.setContact(customer.getContact());
			}
		}
		
		return customerdto;
	}

}

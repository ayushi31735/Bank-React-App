package com.techlabs.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.Role;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.exception.RoleNotFoundException;
import com.techlabs.bank.exception.UserNotFoundException;
import com.techlabs.bank.exception.UsernameNotAvailableException;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.RoleRepository;
import com.techlabs.bank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User addUser(int roleid,User user) {
		validateUsername(user.getUsername());
		Optional<Role> role = roleRepository.findById(roleid);
//		if(role.isPresent())
//		{
//			user.setRole(role.get());
//		}
//		else
//		{
//			throw new RoleNotFoundException();
//		}
		user.setActive(true);
		return userRepository.save(user);
	}

	public void validateUsername(String username) {
		List<User> users = getUsers();
		for(User user : users)
		{
			System.out.println(users);
			if(user.getUsername().equals(username))
			{
				throw new UsernameNotAvailableException();
			}
		}
		
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> getUsersPageWise(int pageumber, int pagesize) {
		Pageable pageable = PageRequest.of(pageumber, pagesize);
		return userRepository.findAll(pageable);
	}

	@Override
	public List<User> getActiveUsers(boolean active) {
		List<User> users = userRepository.findByActive(active);
		return users;
	}

	@Override
	public User getUsersByUsername(String username) {
		User users = userRepository.findByUsername(username);
		return users;
	}

	@Override
	public void removeUser(int userid) {
		User user = userRepository.findByUseridAndActive(userid, true);
		if(user == null)
		{
			throw new UserNotFoundException("User Not Found",HttpStatus.NOT_FOUND);
		}
		
		List<Customer> customers = customerRepository.findByActive(true);
		for(Customer customer : customers)
		{
			if(customer.getUser().getUserid() == userid)
			{
				List<Account> accounts = accountRepository.findByActive(true);
				for(Account account : accounts)
				{
					if(account.getCustomer().getCustomerid() == customer.getCustomerid())
					{
						account.setActive(false);
						accountService.updateAccount(account);
					}
				}
				customer.setActive(false);
				customerService.updateActiveCustomer(customer);
			}
		}
		user.setActive(false);
		updateUser(user);
		
	}

	private void updateUser(User user) {
		
		userRepository.save(user);
		
	}

	@Override
	public boolean editUser(User user) {
		User updateduser = new User();
		updateduser.setUserid(user.getUserid());
		updateduser.setUsername(user.getUsername());
		updateduser.setPassword(passwordEncoder.encode(user.getPassword()));
		updateduser.setActive(true);
		List<Role> roles = new ArrayList<Role>();
		
		Role userRole = roleRepository.findByRolename("ROLE_USER").get();
		roles.add(userRole);
		updateduser.setRoles(roles);
		
		userRepository.save(updateduser);
		return true;
	}

}

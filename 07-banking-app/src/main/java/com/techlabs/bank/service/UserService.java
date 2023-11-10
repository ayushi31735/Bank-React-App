package com.techlabs.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.techlabs.bank.entity.User;

public interface UserService {
	
	User addUser(int roleid,User user);
	List<User> getUsers();
	Page<User> getUsersPageWise(int pageumber,int pagesize);
	List<User> getActiveUsers(boolean active);
	User getUsersByUsername(String username);
	void removeUser(int userid);
	boolean editUser(User user);
	
}

package com.techlabs.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.Role;
import com.techlabs.bank.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByActive(boolean active);
	User findByUsername(String username);
	User findByUseridAndActive(int userid,boolean active);
	
	boolean existsByUsername(String username);
	boolean existsByUserid(int userid);
	boolean existsByUseridAndActive(int userid,boolean active);
	
	Role findByUserid(int userid);
	
}

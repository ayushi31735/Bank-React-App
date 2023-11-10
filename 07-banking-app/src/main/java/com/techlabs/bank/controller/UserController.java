package com.techlabs.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.entity.User;
import com.techlabs.bank.exception.RoleNotFoundException;
import com.techlabs.bank.service.UserService;

@RestController
@RequestMapping("/bankapp")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestParam int roleid, @RequestBody User user)
	{
		userService.addUser(roleid,user);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/users")
	public ResponseEntity<Page<User>> getAllUsers(@RequestParam Map<String,String> params)
	{
		Page<User> userPage = null;
		userPage = userService.getUsersPageWise(0, userService.getUsers().size());
		
		if(userPage.getTotalElements() == 0)
		{
			throw new RoleNotFoundException();
		}
		
		return new ResponseEntity<>(userPage,HttpStatus.OK);
	}
	
	@GetMapping("/users/activeusers")
	public List<User> getActiveUsers()
	{
		return userService.getActiveUsers(true);
	}
	
	@GetMapping("/users/byusername")
	public User getUsersByUsername(@RequestBody String username)
	{
		return userService.getUsersByUsername(username);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/users")
	public void removeUser(@RequestParam int userid)
	{
		userService.removeUser(userid);
	}
	
	@PutMapping("/user")
	public ResponseEntity<String> updateUser(@RequestBody User user)
	{
		boolean check = userService.editUser(user);
		if(check == true)
		{
			return ResponseEntity.ok().body("Password Updated Successfully");
		}
		return ResponseEntity.ok().body("Sorry... Please Try Again");
	}
}

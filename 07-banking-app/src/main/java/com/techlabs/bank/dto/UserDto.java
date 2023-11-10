package com.techlabs.bank.dto;

import com.techlabs.bank.entity.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int userid;
	private String username;
	private String password;
	private Role role;
	
}

package com.techlabs.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.techlabs.bank.dto.RoleDto;
import com.techlabs.bank.entity.Role;

public interface RoleService {
	
	Role addRole(Role role);
	List<Role> getRoles();
	List<RoleDto> getRolesDto();
	Page<Role> getRolesPageWise(int pageumber,int pagesize);
	List<Role> getUsersByRoleid(int roleid);
	
}

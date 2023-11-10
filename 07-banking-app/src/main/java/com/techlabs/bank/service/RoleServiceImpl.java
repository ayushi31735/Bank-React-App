package com.techlabs.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.RoleDto;
import com.techlabs.bank.entity.Role;
import com.techlabs.bank.exception.RoleNotFoundException;
import com.techlabs.bank.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role addRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	@Override
	public List<RoleDto> getRolesDto() {
		List<Role> dbRole = roleRepository.findAll();
		RoleDto roleDto = null;
		List<RoleDto> roledto = new ArrayList<RoleDto>();
		for(Role role : dbRole)
		{
			roleDto = new RoleDto();
			roleDto.setRoleid(role.getRoleid());
			roleDto.setRolename(role.getRolename());
			roledto.add(roleDto);
		}
		return roledto;
	}

	@Override
	public Page<Role> getRolesPageWise(int pageumber, int pagesize) {
		Pageable pageable = PageRequest.of(pageumber, pagesize);
		return roleRepository.findAll(pageable);
	}

	@Override
	public List<Role> getUsersByRoleid(int roleid) {
		Optional<Role> roles = roleRepository.findById(roleid); 
		List<Role> role = new ArrayList<Role>();
		if(roles.isPresent())
		{
			role.add(roles.get());
		}
		else
		{
			throw new RoleNotFoundException();
		}
		return role;
	}

}

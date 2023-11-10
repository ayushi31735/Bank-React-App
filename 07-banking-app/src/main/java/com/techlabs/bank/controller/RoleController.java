package com.techlabs.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.RoleDto;
import com.techlabs.bank.entity.Role;
import com.techlabs.bank.exception.RoleNotFoundException;
import com.techlabs.bank.service.RoleService;

@RestController
@RequestMapping("/bankapp")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/roles")
	public ResponseEntity<Role> addRole(@RequestBody Role role)
	{
		roleService.addRole(role);
		return ResponseEntity.ok(role);
	}
	
	@GetMapping("/roles")
	public List<RoleDto> getAllRoles()
	{
		return roleService.getRolesDto();
	}
	
	@GetMapping("/roles/withallusers")
	public List<Role> getRolesWithUsers()
	{
		return roleService.getRoles();
	}
	
	@GetMapping("/roles/byroleid")
	public List<Role> getUsersByRoleid(@RequestParam int roleid)
	{
		return roleService.getUsersByRoleid(roleid);
	}
	
	@GetMapping("/roles/pagewise")
	public ResponseEntity<Page<Role>> getRoleByRolename(@RequestParam Map<String,String> params)
	{
		Page<Role> rolePage = null;
		rolePage = roleService.getRolesPageWise(0, roleService.getRoles().size());
		
		if(rolePage.getTotalElements() == 0)
		{
			throw new RoleNotFoundException();
		}
		
		return new ResponseEntity<>(rolePage,HttpStatus.OK);
	}

}

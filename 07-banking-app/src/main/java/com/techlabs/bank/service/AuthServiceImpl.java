package com.techlabs.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.LoginDto;
import com.techlabs.bank.dto.RegisterDto;
import com.techlabs.bank.entity.Role;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.exception.UserApiException;
import com.techlabs.bank.repository.RoleRepository;
import com.techlabs.bank.repository.UserRepository;
import com.techlabs.bank.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService{
	
	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private JwtTokenProvider jwtTokenProvider;
	private PasswordEncoder passwordEncoder;
	
	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.jwtTokenProvider = jwtTokenProvider;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String login(LoginDto loginDto) {
		
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			System.out.println("Auth==>"+authentication);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtTokenProvider.generateToken(authentication);
			return token;
		}
		catch(BadCredentialsException e)
		{
			return e.getMessage();
		}
	}

	@Override
	public String registerUser(RegisterDto registerDto) {
		
		if(userRepository.existsByUsername(registerDto.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST,"User Already Exists");
		
		User user = new User();
		user.setUsername(registerDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		List<Role> roles = new ArrayList<Role>();
		
		Role userRole = roleRepository.findByRolename("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		
		user.setActive(true);
		
		userRepository.save(user);
		 
		return "User Registered Successfully";
	}

	@Override
	public String registerAdmin(RegisterDto registerDto) {
		

		if(userRepository.existsByUsername(registerDto.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST,"User Already Exists");
		
		User user = new User();
		user.setUsername(registerDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		List<Role> roles = new ArrayList<Role>();
		
		Role userRole = roleRepository.findByRolename("ROLE_ADMIN").get();
		roles.add(userRole);
		user.setRoles(roles);
		user.setActive(true);
		
		userRepository.save(user);
		System.out.println(user);
		 
		return "Admin Registered Successfully";
	}
}

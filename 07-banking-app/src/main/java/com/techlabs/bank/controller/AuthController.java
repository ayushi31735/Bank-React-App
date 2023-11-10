package com.techlabs.bank.controller;

import java.nio.charset.StandardCharsets; 
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.JwtAuthResponse;
import com.techlabs.bank.dto.LoginDto;
import com.techlabs.bank.dto.RegisterDto;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.service.AuthService;
import com.techlabs.bank.service.UserService;
import javax.crypto.SecretKey;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(value="*")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;
	
	private Cipher cipher;
	SecretKey key;
	
//	@PostMapping(value = {"/login" , "/sign"})
//	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto)
//	{
//		String token = authService.login(loginDto);
//		
//		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
//		jwtAuthResponse.setAccessToken(token);
//		
//		return ResponseEntity.ok(jwtAuthResponse);
//	}
	
	@PostMapping(value = {"/login" , "/sign"})
	public ResponseEntity<User> login(@RequestBody LoginDto loginDto)
	{
		String token = authService.login(loginDto);
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Auth", token);
	    
	    User user = userService.getUsersByUsername(loginDto.getUsername());
	    
		return ResponseEntity.ok().headers(headers).body(user);
	}
	
//	 public String decrypt(String encryptedString) {
//	        String decryptedText=null;
//	        try {
//	            cipher.init(Cipher.DECRYPT_MODE, key);
//	            byte[] encryptedText = Base64.decodeBase64(encryptedString);
//	            byte[] plainText = cipher.doFinal(encryptedText);
//	            decryptedText= new String(plainText);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        return decryptedText;
//	    }
//	
//	@PreAuthorize("hasRole('ADMIN')") 
	  @GetMapping("/validate")   
	     public ResponseEntity<String> validateuser(@RequestHeader("Authorization") String authorizationHeader) {   
//	         if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {   
	             // Extract the token from the header   
	             String jwtToken = authorizationHeader.substring(7); // 7 is the length of "Bearer "   
	                
	             // Now you have the JWT token   
	             System.out.println("JWT Token: " + jwtToken);   
	                
	             String[] jwtParts = jwtToken.split("\\.");  
	             String head = jwtParts[0];  
	             String encodedPayload = jwtParts[1];  
	              
	             byte[] decodedPayload = Base64.getDecoder().decode(encodedPayload); 
	             String payload = new String(decodedPayload, StandardCharsets.UTF_8); 
	 
	               
	             System.out.println("Payload--->"+payload);  
	  
	             return ResponseEntity.ok(payload);  
	     }
	
	@PostMapping("/registeruser")
	public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto)
	{
		System.out.println(registerDto);
		String response = authService.registerUser(registerDto);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/registeradmin")
	public ResponseEntity<String> registerAdmin(@RequestBody RegisterDto registerDto)
	{
		String response = authService.registerAdmin(registerDto);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
}

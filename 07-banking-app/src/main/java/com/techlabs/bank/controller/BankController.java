package com.techlabs.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.BankDto;
import com.techlabs.bank.entity.Bank;
import com.techlabs.bank.service.BankService;

@RestController
@RequestMapping("/bankapp")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/banks") 
	 public ResponseEntity<Bank> addBank(@RequestBody Bank bank) 
	 { 
		bankService.addBank(bank);
		return ResponseEntity.ok(bank); 
	 }
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/banks")
	public ResponseEntity<Page<BankDto>> getAllBanks(@RequestParam int pageNumber,@RequestParam int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		Page<BankDto> banks = bankService.getAllBanks(pageable);
		System.out.println("total records are "+banks.getTotalElements());
		HttpHeaders headers = new HttpHeaders();
	    headers.add("X-Total-Count", String.valueOf(banks.getTotalElements()));
	    return ResponseEntity.ok().headers(headers).body(banks);
			
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/banks/byabbrevation")
	public List<Bank> getBankByAbbrevation(@RequestParam String abbrevation)
	{
		return bankService.getBankByAbbrevation(abbrevation);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/banks/byabbrevationactive")
	public List<Bank> getBankByAbbrevationAndActive(@RequestParam String abbrevation)
	{
		return bankService.getBankByAbbrevationAndActive(abbrevation);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/banks/branch")
	public List<Bank> getBankByBranch(@RequestParam String branch)
	{
		return bankService.getBankByBranch(branch);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/banks/activebranch")
	public List<Bank> getBankByActiveBranch(@RequestParam String branch)
	{
		return bankService.getBankByActiveBranch(branch);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/banks/activebanks")
	public List<Bank> getActive()
	{
		return bankService.getActiveBanks();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/banks")
	public ResponseEntity<String> removeBank(@RequestParam int bankid)
	{
		String checkMsg;
		boolean check = bankService.removeBank(bankid);
		if(check == true)
		{
			checkMsg = "Bank Deleted Successfully";
		}
		else
		{
			checkMsg = "Bank Not Found"; 
		}
		return ResponseEntity.ok().body(checkMsg);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/banks")
	public String updateBank(@RequestBody Bank bank)
	{
		boolean check = bankService.updateBank(bank);
		if(check == true)
			return "Bank Updated Successfully";
		else
			return "Bank Not Found";
	}
	
	@GetMapping("/banks/bybankname")
	public List<BankDto> getBankByBankname(@RequestParam String bankname)
	{
		List<BankDto> banks = bankService.getBanksByBankname(bankname);
		return banks;
	}
	
	@GetMapping("/banks/bankid")
	public ResponseEntity<Integer> getBankId(@RequestParam String bankname,@RequestParam String branch)
	{
		int bankid = bankService.getBankid(bankname, branch);
		return ResponseEntity.ok().body(bankid);
	}
}

package com.techlabs.bank.mapper;

import com.techlabs.bank.dto.BankDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Bank;

public class BankMapper {

	public static BankDto BanktobankDto(Bank bank) {
        // Implement your mapping logic from Bank to BankDto here
        BankDto bankDto = new BankDto();
        bankDto.setBankName(bank.getBankname());
        bankDto.setBranch(bank.getBranch());
        bankDto.setAbbrevation(bank.getAbbrevation());
        bankDto.setIfsc(bank.getIfscCode());
        bankDto.setBankid(bank.getBankid());
       
        // Map other properties as needed
        return bankDto;
    }


    public static Bank bankDtotobank(BankDto bankDto) {
        
        Bank bank = new Bank();
        bank.setBankname(bankDto.getBankName());
        bank.setBranch(bankDto.getBranch());
        bank.setAbbrevation(bankDto.getAbbrevation());
        bank.setIfscCode(bankDto.getIfsc());
        return bank;
        
        

}
}

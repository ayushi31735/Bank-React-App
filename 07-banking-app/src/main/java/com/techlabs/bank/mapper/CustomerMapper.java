package com.techlabs.bank.mapper;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.entity.Customer;

public class CustomerMapper {
	
	public static CustomerDto CustomertoCustomerDto(Customer customer) {
        CustomerDto customerdto = new CustomerDto();
        customerdto.setCustomerid(customer.getCustomerid());
        customerdto.setFirstname(customer.getFirstname());
        customerdto.setLastname(customer.getLastname());
        customerdto.setContact(customer.getContact());
        customerdto.setEmail(customer.getEmail());
       
        return customerdto;
    }
	
}

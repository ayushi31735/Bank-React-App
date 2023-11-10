package com.techlabs.bank.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="bank")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bank {
	
	@Column(name="bankid")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bankid;
	
	@Column(name="bankname")
	@NotBlank
	@Pattern(regexp="[A-Z|a-z| ]*",message="Please enter a valid Bank Name")
	private String bankname;
	
	@Column(name="branch")
	@NotBlank
	@Pattern(regexp="[A-Z|a-z| ]*",message="Please enter a valid Bank Branch")
	private String branch;
	
	@Column(name="abbrevation")
	@NotBlank
	@Pattern(regexp="[A-Z]*",message="Please enter a valid Bank Abbrevation")
	private String abbrevation;
	
	@Column(name="ifsccode")
//	@NotBlank
	private String ifscCode;
	
	@Column(name="active")
	private boolean active;
	
	@OneToMany(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			mappedBy = "bank",fetch = FetchType.LAZY)
	private List<Account> accounts;

	@Override
	public String toString() {
		return "Bank [bankid=" + bankid + ", bankname=" + bankname + ", branch=" + branch + ", abbrevation="
				+ abbrevation + ", ifscCode=" + ifscCode + ", active=" + active + ", accounts=" + accounts + "]";
	}
	
}

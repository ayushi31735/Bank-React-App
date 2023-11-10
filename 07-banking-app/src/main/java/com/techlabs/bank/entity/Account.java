package com.techlabs.bank.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="account")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
	
	@Column(name="accountnumber")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int accountnumber;
	
	@Column(name="balance")
	@NotNull
	private double balance;
	
	@Column(name="active")
	public boolean active;
	
	@ManyToOne
	@JoinColumn(name="customerid")
	@JsonBackReference
	@JsonIgnore
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="bankid")
	@JsonBackReference
	@JsonIgnore
	private Bank bank;
	
	@JsonManagedReference
	@OneToMany(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			mappedBy = "account",fetch = FetchType.LAZY)
	private List<Transaction> transactions;

	@Override
	public String toString() {
		return "Account [accountnumber=" + accountnumber + ", balance=" + balance + ", active=" + active + ", customer="
				+ customer + ", bank=" + bank + ", transactions=" + transactions + "]";
	}
	
	
}

package com.techlabs.bank.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="transaction")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
	
	@Column(name="transactionid")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionid;
	
	@Column(name="transactiontype")
	@Pattern(regexp="[A-Z|a-z]*",message="Please enter a valid Transaction Type")
	private String transactionType;
	
	@Column(name="transactiondate")
	private Date transactionDate;
	
	@Column(name="amount")
	@NotNull
	private double amount;
	
	@Column(name="fromaccount")
	private int fromAccount;
	
	@Column(name="toaccount")
	private int toAccount;
	
	@Column(name="currentbalance")
	private double currentBalance;
	
	@ManyToOne
	@JoinColumn(name="accountnumber")
	@JsonBackReference
	@JsonIgnore
	private Account account;

	@Override
	public String toString() {
		return "Transaction [transactionid=" + transactionid + ", transactionType=" + transactionType
				+ ", transactionDate=" + transactionDate + ", amount=" + amount + ", toAccount=" + toAccount
				+ ", currentBalance=" + currentBalance + ", account=" + account + "]";
	}
	
}

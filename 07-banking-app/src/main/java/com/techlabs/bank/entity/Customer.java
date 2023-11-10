package com.techlabs.bank.entity;

import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customer")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
	
	@Column(name="customerid")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerid;
	
	@Column(name="firstname")
	@NotBlank
	@Pattern(regexp="[A-Z][a-z]*",message="Please enter a valid First Name")
	private String firstname;
	
	@Column(name="lastname")
	@Pattern(regexp="[A-Z][a-z]*",message="Please enter a valid Last Name")
	private String lastname;
	
	@Column(name="contact")
	@NotNull
	private long contact;
	
	@Column(name="email")
	@Pattern(regexp="[a-zA-Z0-9_&*#-]+[@][a-z]+[.][a-z]{2,3}",message="Please enter a valid email Id")
	private String email;
	
	@Column(name="active")
	private boolean active;
	
	@JoinColumn(name = "userid")
	@OneToOne(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private User user;
	
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			mappedBy = "customer",fetch = FetchType.LAZY)
	private List<Account> accounts;

//	@Override
//	public String toString() {
//		return "Customer [customerid=" + customerid + ", firstName=" + firstname + ", lastName=" + lastname
//				+ ", contact=" + contact + ", email=" + email + ", active=" + active + ", user=" + user + ", accounts="
//				+ accounts + "]";
//	}
	
	
}

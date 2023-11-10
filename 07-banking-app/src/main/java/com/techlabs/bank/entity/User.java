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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
	
	@Column(name="userid")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	@NotBlank
//	@Pattern(regexp="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}",
//				message="Please enter a Valid Password --> Password Must Contain 8 to 16 Characters \n"
//						+ "One Capital Letter\n"
//						+ "One Small Letter\n"
//						+ "One Numberic Value\n"
//						+ "One Special Character")
	private String password;
	
	@Column(name="active")
	private boolean active;
	
//	@JoinColumn(name = "customerid")
//	@OneToOne(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	private Customer customer;
	
	@JsonManagedReference
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH} , fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", 
               joinColumns = @JoinColumn(name = "user_id"), 
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles ;

//	@Override
//	public String toString() {
//		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", active=" + active
//				+ ", roles=" + roles + "]";
//	}
	
}

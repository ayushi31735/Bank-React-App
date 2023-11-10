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
@Table(name="role")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
	
//	@Column(name="roleid")
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int roleid;
//	
//	@Column(name="rolename")
//	@NotBlank
//	@Pattern(regexp="[A-Z][a-z]*[_]{1}[A-Z][a-z]*",message="Please enter a valid Role Name")
//	private String rolename;
//	
//	@OneToMany(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
//			mappedBy = "role",fetch = FetchType.LAZY)
//	private List<User> users;
//
//	@Override
//	public String toString() {
//		return "Role [roleid=" + roleid + ", rolename=" + rolename + "]";
//	}
	
	@Column(name="roleid")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roleid;
	
	@Column(name="rolename")
	@NotBlank
	@Pattern(regexp="[A-Z][a-z]*[_]{1}[A-Z][a-z]*",message="Please enter a valid Role Name")
	private String rolename;

//	@Override
//	public String toString() {
//		return "Role [roleid=" + roleid + ", rolename=" + rolename + "]";
//	}
	
}

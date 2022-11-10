package com.OrderApplicarion.payloads;

import java.util.HashSet;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.OrderApplicarion.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;





public class EmployeDto {
	
	
	private int id;
	@NotEmpty
	@Size(min = 4 , message ="User must be min of 4 characters !!")
	private String name;
	@NotEmpty(message= "Email is required !!")
	@Email(message = "Email address is not valid !!")
	private String email;
	@NotEmpty
	@Size(min=3, max = 10, message ="password must be min of 3 chars and max of 10 chars !!")
	private String password;
	//@NotEmpty
	//private String employeId;
	
	
	
	public int getId() {
		return id;
	}
	
	public EmployeDto(String name, String email, String password, String employeId) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		//this.employeId = employeId;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//password can't be sent to client
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
//	public String getEmployeId() {
//		return employeId;
//	}
//	public void setEmployeId(String employeId) {
//		this.employeId = employeId;
//	}

	public EmployeDto() {
		super();
		
	}

	private Set<RoleDto> roles=new HashSet<>();



	public Set<RoleDto> getRoles() {
		return roles;
	}

	//public void setRoles(Set<RoleDto> roles) {
		//this.roles = roles;
   //	}
	
}

package com.OrderApplicarion.entities;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.OrderApplicarion.payloads.EmployeDto;

import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@NoArgsConstructor
public class Employe implements UserDetails{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	//@Size(min = 4 , message ="User must be min of 4 characters !!")
	private String name;
	@Column(name="email", nullable=false ,unique=true)
	private String email;
	private String password;
	//@Column(name="employe_id")
	//private String employeId;
	@OneToMany(targetEntity = OrderList.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "cos_fk" ,referencedColumnName = "name")
	private List<OrderList> orderList;
	
	
	public List<OrderList> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderList> orderList) {
		this.orderList = orderList;
	}
	public int getId() {
		return id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public String getEmployeId() {
//		return employeId;
//	}
//	public void setEmployeId(String employeId) {
//		this.employeId = employeId;
//	}
	
	public Employe(int id, String name, String email, String password, String employeId, Set<Role> roles,List<OrderList> orderList) {
		super();
		this.orderList=orderList;
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		//this.employeId = employeId;
		this.roles = roles;
	}
	


	
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns = @JoinColumn(name="employe", referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id"))
	private Set<Role> roles=new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<SimpleGrantedAuthority>authories = this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return authories;
	}
	@Override
	public String getUsername() {
		
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	}


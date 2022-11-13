package com.OrderApplicarion.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class OrderList {
	@Id
	@GeneratedValue
	private int id;
	private String account;
	private String due_date;
	
	@OneToMany(targetEntity = Products.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "op_fk" ,referencedColumnName = "id")
	private List<Products> products;
	
	
	public OrderList(int id, String account, String due_date, List<Products> products) {
		super();
		this.id = id;
		this.account = account;
		this.due_date = due_date;
		this.products = products;
	}
	public OrderList() {
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "OrderList [id=" + id + ", account=" + account + ", due_date=" + due_date + ", products=" + products
				+ "]";
	}
	
	
	

}

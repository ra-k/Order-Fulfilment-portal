package com.OrderApplicarion.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Products {
	@Id
	private int pid;
	private String category;
	private String name;
	private int quantity;
	
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Products(int pid, String category, String name, int quantity) {
		super();
		this.pid = pid;
		this.category = category;
		this.name = name;
		this.quantity = quantity;
	}
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Products [pid=" + pid + ", category=" + category + ", name=" + name + ", quantity=" + quantity + "]";
	}
	
	

}

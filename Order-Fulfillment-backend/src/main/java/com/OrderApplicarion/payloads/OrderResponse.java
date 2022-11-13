
package com.OrderApplicarion.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OrderResponse {
	
	private String account;
	private String due_date;
	private int pid;
	private String category;
	private String name;
	private int quantity;
	
	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderResponse(String account, String due_date,  String name) {
		super();
		this.account = account;
		this.due_date = due_date;
		this.name = name;
	}
	
	public OrderResponse(String account, String due_date, String name,int pid, String category,  int quantity) {
		super();
		this.account = account;
		this.due_date = due_date;
		this.pid = pid;
		this.category = category;
		this.name = name;
		this.quantity = quantity;
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
	@Override
	public String toString() {
		return "OrderResponse [account=" + account + ", due_date=" + due_date + ", pid=" + pid + ", category="
				+ category + ", name=" + name + ", quantity=" + quantity + "]";
	}}

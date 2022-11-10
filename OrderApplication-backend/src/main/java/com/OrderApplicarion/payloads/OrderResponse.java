package com.OrderApplicarion.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OrderResponse {
	 private String account;
	 private String name;
	 private String due_date;
	 
	 
	 
	 
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OrderResponse(String account, String name,String due_date) {
		super();
		this.due_date=due_date;
		this.account = account;
		this.name = name;
	}
	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderResponse [account=" + account + ", name=" + name + "]";
	}
	private int quantity;

	 
	 
}

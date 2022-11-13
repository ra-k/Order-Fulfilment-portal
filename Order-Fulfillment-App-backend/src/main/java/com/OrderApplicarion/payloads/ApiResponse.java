package com.OrderApplicarion.payloads;

import lombok.AllArgsConstructor;




@AllArgsConstructor
public class ApiResponse {
	
	private String message;
	private boolean sucess;
	public ApiResponse(String message, boolean sucess) {
		super();
		this.message = message;
		this.sucess = sucess;
	}
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSucess() {
		return sucess;
	}
	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}
	

}

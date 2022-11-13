package com.OrderApplicarion.payloads;


public class JwtAuthResponse {

	private String token;
    private EmployeDto employe;
    
	public EmployeDto getEmploye() {
		return employe;
	}

	public void setEmploye(EmployeDto employe) {
		this.employe = employe;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

package com.OrderApplicarion.services;

import java.util.List;

import com.OrderApplicarion.entities.Employe;
import com.OrderApplicarion.payloads.EmployeDto;

public interface EmployeService {

	//for register
	
	EmployeDto registerNewEmploye(EmployeDto employe);
	
	
	
	//to create 
	EmployeDto createEmploye(EmployeDto employe);
	EmployeDto updateEmploye(EmployeDto employe, Integer employeId); 
	EmployeDto getEmployeById(Integer employeId);
	List<EmployeDto> getAllEmploye();
	void deleteEmploye(Integer employeId);
}

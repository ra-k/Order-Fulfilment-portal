package com.OrderApplicarion.controllers;

import java.util.List;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrderApplicarion.payloads.ApiResponse;
import com.OrderApplicarion.payloads.EmployeDto;
import com.OrderApplicarion.services.EmployeService;

@RestController
@RequestMapping("/api/user")
public class EmployeController {

	@Autowired
	private EmployeService employeService;
	
	//post-create user
	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/")
	public ResponseEntity<EmployeDto>createEmploye(@Valid@RequestBody EmployeDto employeDto)
	{
		EmployeDto createEmployeDto = this.employeService.createEmploye(employeDto);
		return new ResponseEntity<>(createEmployeDto , HttpStatus.CREATED);
	}
	//put update user
	
	@PutMapping("/{employeId}")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<EmployeDto> updateEmploye(@RequestBody EmployeDto employeDto , @PathVariable Integer employeId)
	{
		EmployeDto  updatedEmploye= this.employeService.updateEmploye(employeDto , employeId);
		return ResponseEntity.ok(updatedEmploye);
		
	}
	
	
	
	//delete delete user
	@PreAuthorize("hasRole('Admin')")
     @DeleteMapping("/{employeId}")
	  public ResponseEntity<ApiResponse>deleteEmploye(@PathVariable Integer employeId){
		this.deleteEmploye(employeId);
	    return new ResponseEntity<ApiResponse>(new ApiResponse("Employe deleted Successfully", true),HttpStatus.OK);
	}
     
 
     
	//Get user get
	@PreAuthorize("hasRole('Admin')")
     @GetMapping("/")
	public ResponseEntity<List<EmployeDto>>getAllEmploye(){
		return ResponseEntity.ok(this.employeService.getAllEmploye());
	}
	
   //Get user get
	@PreAuthorize("hasRole('Admin')")
     @GetMapping("/{employeId}")
     public ResponseEntity<EmployeDto> getSingleEmploye(@PathVariable Integer employeId){
     return ResponseEntity.ok(this.employeService.getEmployeById(employeId));
	
	
     }
     
   
}

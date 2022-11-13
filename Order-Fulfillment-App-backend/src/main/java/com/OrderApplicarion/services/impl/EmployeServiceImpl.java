package com.OrderApplicarion.services.impl;

import java.util.List;


import org.springframework.stereotype.Service;



import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.OrderApplicarion.exceptions.ResourceNotFoundException;
import com.OrderApplicarion.config.AppConstants;
import com.OrderApplicarion.entities.Employe;
import com.OrderApplicarion.entities.Role;
import com.OrderApplicarion.payloads.EmployeDto;
import com.OrderApplicarion.repositories.EmployeRepo;
import com.OrderApplicarion.repositories.RoleRepo;
import com.OrderApplicarion.services.EmployeService;






@Service
public class EmployeServiceImpl implements EmployeService {

	@Autowired
	private EmployeRepo employeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	  
	@Autowired
	private RoleRepo roleRepo;
	
	
	@Override
	public EmployeDto createEmploye(EmployeDto employeDto) {
		Employe employe = this.dtoToEmploye(employeDto);
		Employe savedEmploye = this.employeRepo.save(employe);
		return this.employeToDto(savedEmploye);
	}

	@Override
	public EmployeDto updateEmploye(EmployeDto employeDto, Integer employeId) {
		Employe employe = this.employeRepo.findById(employeId)
				.orElseThrow(()-> new ResourceNotFoundException("Employe","Id",employeId));
		
		employe.setName(employeDto.getName());
		employe.setEmail(employeDto.getEmail());
		employe.setPassword(employeDto.getPassword());
		employe.setEmployeId(employeDto.getEmployeId());
		
		Employe updatedEmploye = this.employeRepo.save(employe);
		EmployeDto employeDto1 = this.employeToDto(updatedEmploye);
		return employeDto1;
	}

	@Override
	public EmployeDto getEmployeById(Integer employeId) {
		Employe employe= this.employeRepo.findById(employeId).orElseThrow(()->new ResourceNotFoundException("Employe", "Id" , employeId));
		return this.employeToDto(employe);
	}


	
	
	@Override
	public List<EmployeDto> getAllEmploye() {
		List<Employe> employes = this.employeRepo.findAll();
		List<EmployeDto> employeDtos = employes.stream().map(employe -> this.employeToDto(employe)).collect(Collectors.toList());
		
		return employeDtos;
	}

	@Override
	public void deleteEmploye(Integer employeId) {
	Employe employe = this.employeRepo.findById(employeId).orElseThrow(()-> new ResourceNotFoundException("Employe","Id",employeId));
     this.employeRepo.delete(employe);
	}
	
	private Employe dtoToEmploye(EmployeDto employeDto)
	{
		Employe employe = this.modelMapper.map(employeDto,Employe.class);
//		employe.setId(employeDto.getId());
//		employe.setName(employeDto.getName());
//		employe.setEmail(employeDto.getEmail());
//		employe.setPassword(employeDto.getPassword());
//		employe.setEmployeId(employeDto.getEmployeId());
		return employe;
	}
	public EmployeDto employeToDto(Employe employe)
	{
		EmployeDto employeDto = this.modelMapper.map(employe, EmployeDto.class);
		
		return employeDto;
		
	}

	@Override
	public EmployeDto registerNewEmploye(EmployeDto employeDto) {
		
		Employe employe =this.modelMapper.map(employeDto , Employe.class);
		
		//encode the password
		employe.setPassword(this.passwordEncoder.encode(employe.getPassword()));
		
		// roles
	   Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

	  
	    employe.getRoles().add(role);
		
		Employe newEmploye = this.employeRepo.save(employe);
		return this.modelMapper.map(newEmploye,EmployeDto.class );
	}	
	
}

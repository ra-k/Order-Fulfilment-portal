package com.OrderApplication.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OrderApplicarion.entities.Employe;
import com.OrderApplicarion.entities.Role;
import com.OrderApplicarion.payloads.EmployeDto;
import com.OrderApplicarion.payloads.RoleDto;

@Component
public class RoleConverter {

@Autowired
 ModelMapper  modelMapper;
 
private Role dtoToEntity(RoleDto roleDto)
{
	Role role = this.modelMapper.map(roleDto,Role.class);

	return role;
}
public RoleDto entityToDto(Role role)
{
	RoleDto roleDto = this.modelMapper.map(role, RoleDto.class);
	
	return roleDto;
	
}
}

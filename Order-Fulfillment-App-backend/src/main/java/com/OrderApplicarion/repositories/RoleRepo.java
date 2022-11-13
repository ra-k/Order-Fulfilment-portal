package com.OrderApplicarion.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.OrderApplicarion.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	
	 //Optional<Role> findById(Integer role);

}

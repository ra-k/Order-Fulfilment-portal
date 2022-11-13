package com.OrderApplicarion.repositories;




import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OrderApplicarion.entities.Employe;

@Repository
public interface EmployeRepo extends JpaRepository<Employe, Integer>{
Optional<Employe> findByEmail(String email);
	

}

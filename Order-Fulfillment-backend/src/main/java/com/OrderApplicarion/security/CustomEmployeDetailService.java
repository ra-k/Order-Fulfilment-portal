package com.OrderApplicarion.security;


	
	
	


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.stereotype.Service;

	import com.OrderApplicarion.entities.Employe;
	import com.OrderApplicarion.exceptions.ResourceNotFoundException;
	import com.OrderApplicarion.repositories.EmployeRepo;

	@Service
	public class CustomEmployeDetailService implements UserDetailsService {
	    
		@Autowired
		private EmployeRepo employeRepo;
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
			
			Employe employe =this.employeRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("Employe","email :" +username, 0));
			
			return employe;
		}

	}
	
	


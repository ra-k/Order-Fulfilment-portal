package com.OrderApplicarion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.OrderApplicarion.exceptions.ApiException;
import com.OrderApplicarion.payloads.EmployeDto;
import com.OrderApplicarion.payloads.JwtAuthRequest;
import com.OrderApplicarion.payloads.JwtAuthResponse;
import com.OrderApplicarion.security.JwtTokenHelper;
import com.OrderApplicarion.services.EmployeService;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.OrderApplicarion.exceptions.ApiException;
//import com.OrderApplicarion.payloads.EmployeDto;
//import com.OrderApplicarion.payloads.JwtAuthRequest;
//import com.OrderApplicarion.payloads.JwtAuthResponse;
//import com.OrderApplicarion.security.JwtTokenHelper;
//import com.OrderApplicarion.services.EmployeService;

@RestController
	@RequestMapping("/api/v1/auth/")
	public class AuthController {

		@Autowired
		private JwtTokenHelper jwtTokenHelper;
        
		@Autowired
		private EmployeService employeService;
		@Autowired
		private UserDetailsService userDetailsService;

		@Autowired
		private AuthenticationManager authenticationManager;

		@PostMapping("/login")
		public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

			this.authenticate(request.getUsername(), request.getPassword());

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

			String token = this.jwtTokenHelper.generateToken(userDetails);

			JwtAuthResponse response = new JwtAuthResponse();
			response.setToken(token);
			return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);

		}

		private void authenticate(String username, String password) throws Exception {

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					password);

			try {

				this.authenticationManager.authenticate(authenticationToken);

			} catch (BadCredentialsException e) {
					System.out.println("Invalid Detials !!");
					throw new ApiException("Invalid username or password !!");
			}

		}
		
		//register new user Api
		
		@PostMapping("/register")
		public ResponseEntity<EmployeDto> registerEmploye(@RequestBody EmployeDto employeDto )
		{
			EmployeDto registeredUser=this.employeService.registerNewEmploye(employeDto);
		   return new ResponseEntity<EmployeDto>(registeredUser, HttpStatus.CREATED);
		
		}
	
	
}

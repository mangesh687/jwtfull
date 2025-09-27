package com.myapp.section1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.section1.model.Customer;
import com.myapp.section1.repository.CustomerRepository;

@RestController
public class UserController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer){
		try {
			String hashPwd=passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashPwd);
			Customer savecustomer=customerRepository.save(customer);
			if(savecustomer.getId()>0) {
				return ResponseEntity.status(HttpStatus.CREATED).body("given userdetails are successfuly");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user registarion failed");
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("an exception occurrend :"+e.getMessage());
		}
		
		
	}

}

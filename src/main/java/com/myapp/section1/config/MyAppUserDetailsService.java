package com.myapp.section1.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myapp.section1.model.Customer;
import com.myapp.section1.repository.CustomerRepository;
@Service
public class MyAppUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Customer customer=customerRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User details not found for the user: "+username));
	List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
     return new User(customer.getEmail(), customer.getPwd(), authorities);
	}

}

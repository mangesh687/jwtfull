package com.myapp.section1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class MyAppUsernamePwdAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private MyAppUserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username=authentication.getName();
		String pwd=authentication.getCredentials().toString();
		UserDetails userDetails =userDetailsService.loadUserByUsername(username);
		if(passwordEncoder.matches(pwd, userDetails.getPassword())) {
			//check age >18
			return new UsernamePasswordAuthenticationToken(username, pwd,userDetails.getAuthorities());
		}else {
			throw new BadCredentialsException("invalid password");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		//abstactUserDetailsAuthenticationProvider.java
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

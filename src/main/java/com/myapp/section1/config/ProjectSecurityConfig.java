package com.myapp.section1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class ProjectSecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(x->x.disable())
		.authorizeHttpRequests(x->x.requestMatchers("/account","/balance","/loans","/cards").authenticated()
				.requestMatchers("/notices","/contact","/error","/welcome","/register").permitAll()
				)
		.formLogin(withDefaults -> {})  
        .httpBasic(withDefaults -> {}); 
		return http.build();
		
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	// this use for complex password if you use normal password not work 
	@Bean
	public CompromisedPasswordChecker compromisedPasswordChecker() {
		return new HaveIBeenPwnedRestApiPasswordChecker();
	}

}

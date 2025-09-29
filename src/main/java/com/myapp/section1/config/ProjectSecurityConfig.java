package com.myapp.section1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import com.myapp.section1.exception.CustomBasicAuthenticationEntryPoint;
import com.myapp.section1.exception.DeniedHandeler;

@Configuration
public class ProjectSecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http
		//handel timeout session  and sessions limit 
		.sessionManagement(smc -> smc.invalidSessionUrl("/invalidSession").maximumSessions(1))
		//https trafic to handel 
	//	.requiresChannel(rcf->rcf.anyRequest().requiresSecure()) // for prod
    	.requiresChannel(rcf->rcf.anyRequest().requiresInsecure()) // for dev
		.csrf(x->x.disable())
		.authorizeHttpRequests(x->x.requestMatchers("/account","/balance","/loans","/cards").authenticated()
				.requestMatchers("/notices","/contact","/error","/welcome","/register","/invalidSession").permitAll()
				)
		.formLogin(withDefaults -> {}) ; 
       http .httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())); 
       http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new DeniedHandeler()));
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

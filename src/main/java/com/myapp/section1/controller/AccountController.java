package com.myapp.section1.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class AccountController {

	@GetMapping("/account")
	public String getAccount() {
		return "get account";
	}
	
}

package com.myapp.section1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

	@GetMapping("/balance")
	public String getBalance() {
		return "welocme to balance";
	}
}

package com.myapp.section1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

	@GetMapping("/cards")
	public String getcards() {
		return "welocme to cards ";
	}
}

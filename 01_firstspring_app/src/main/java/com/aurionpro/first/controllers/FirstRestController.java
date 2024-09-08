package com.aurionpro.first.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {
	@GetMapping("/hello")
	public String sayhello() {
		return "Hello World !!!!!!!!!!";
	}
}

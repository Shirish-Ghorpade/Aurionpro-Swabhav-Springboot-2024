package com.aurionpro.jwtsecured.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.jwtsecured.dto.JwtAuthResponse;
import com.aurionpro.jwtsecured.dto.LoginDto;
import com.aurionpro.jwtsecured.dto.RegistrationDto;
import com.aurionpro.jwtsecured.entity.User;
import com.aurionpro.jwtsecured.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
	AuthService authService;
	
	@PostMapping("/register")
	ResponseEntity<User> registerUser(@RequestBody RegistrationDto data){
		return ResponseEntity.ok(authService.register(data));
	}
	
	@PostMapping("/login")
	ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDto data){
//		return ResponseEntity.ok(authService.login(data));
		
		String token = authService.login(data);
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		return ResponseEntity.ok(jwtAuthResponse);
	}
	
}

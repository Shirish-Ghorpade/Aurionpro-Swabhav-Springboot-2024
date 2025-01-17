package com.aurionpro.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.model.dto.JwtAuthResponse;
import com.aurionpro.model.dto.LoginDto;
import com.aurionpro.model.dto.RegistrationDto;
import com.aurionpro.model.entity.User;
import com.aurionpro.model.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class AuthController {
	@Autowired
	AuthService authService;

//	@PostMapping("/register")
//	ResponseEntity<User> registerUser(@Valid @RequestBody RegistrationDto data){
//		return ResponseEntity.ok(authService.register(data));
//	}

	@PostMapping("/login")
	ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDto data, HttpServletRequest request,
			@RequestParam("captcha") String captcha) {
		String captchaSession = (String) request.getSession().getAttribute("captcha");
		if (!captcha.equals(captchaSession)) {
			throw new RuntimeException("Entered value of captcha is wrong, please try again !!!");
		}
		String token = authService.login(data);
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		return ResponseEntity.ok(jwtAuthResponse);
	}
}

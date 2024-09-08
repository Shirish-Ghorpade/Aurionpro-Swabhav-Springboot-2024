package com.aurionpro.jwtsecured.service;

import com.aurionpro.jwtsecured.dto.LoginDto;
import com.aurionpro.jwtsecured.dto.RegistrationDto;
import com.aurionpro.jwtsecured.entity.User;

public interface AuthService {
	User register(RegistrationDto registrationDto);

	String login(LoginDto loginDto);
}

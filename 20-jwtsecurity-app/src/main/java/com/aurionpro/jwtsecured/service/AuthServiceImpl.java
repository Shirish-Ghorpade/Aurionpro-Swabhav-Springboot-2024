package com.aurionpro.jwtsecured.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.jwtsecured.dto.LoginDto;
import com.aurionpro.jwtsecured.dto.RegistrationDto;
import com.aurionpro.jwtsecured.entity.Role;
import com.aurionpro.jwtsecured.entity.User;
import com.aurionpro.jwtsecured.exception.UserApiException;
import com.aurionpro.jwtsecured.repository.RoleRepository;
import com.aurionpro.jwtsecured.repository.UserRespository;
import com.aurionpro.jwtsecured.security.JwtTokenProvider;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRespository userRespository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public User register(RegistrationDto registrationDto) {
		if (userRespository.existsByUsername(registrationDto.getUsername())) {
			throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists");
		}
		User user = new User();
		user.setUsername(registrationDto.getUsername());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		List<Role> roles = new ArrayList<Role>();

		Role userRole = roleRepository.findByRoleName(registrationDto.getRoleName());
		roles.add(userRole);
		user.setRoles(roles);
		return userRespository.save(user);
	}

	@Override
	public String login(LoginDto loginDto) {

		try {
			System.out.println(loginDto.getUsername() + " " + loginDto.getPassword());
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtTokenProvider.generateToken(authentication);
			return token;
		} catch (BadCredentialsException e) {
			throw new UserApiException(HttpStatus.UNAUTHORIZED, "Username and password is not valid");
		}
	}

}

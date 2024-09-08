package com.aurionpro.jwtsecured.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserApiException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
}
package com.aurionpro.model.exceptions;

public class StudentNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Student is not found";
	}
}

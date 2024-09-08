package com.aurionpro.mapping.exceptions;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int id;
	private String message;

	public NotFoundException(int id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message + id;
	}
}

package com.aurionpro.connect.exceptions;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int id;

	public CustomerNotFoundException(int id) {
		super();
		this.id = id;
	}

	@Override
	public String getMessage() {

		return "Customer is not found with id : " + id;
	}
}

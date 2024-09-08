package com.aurionpro.connect.exceptions;

public class LoanNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private int id;

	public LoanNotFoundException(int id) {
		super();
		this.id = id;
	}

	@Override
	public String getMessage() {

		return "Loan is not found with id : " + id;
	}
}

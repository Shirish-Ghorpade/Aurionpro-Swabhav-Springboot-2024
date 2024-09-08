package com.aurionpro.connect.exceptions;

public class PaymentNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private int id;

	public PaymentNotFoundException(int id) {
		super();
		this.id = id;
	}

	@Override
	public String getMessage() {

		return "Payment is not found with id : " + id;
	}
}

package com.aurionpro.connect.service;

import java.util.List;

import com.aurionpro.connect.entity.Payment;

public interface PaymentService {
	public List<Payment> getAllPayments();

	public Payment getPaymentById(int paymentId);

	public void addPayment(Payment payment);

	void updatePayment(Payment payment);

	public void deletePayment(int paymentId);
}

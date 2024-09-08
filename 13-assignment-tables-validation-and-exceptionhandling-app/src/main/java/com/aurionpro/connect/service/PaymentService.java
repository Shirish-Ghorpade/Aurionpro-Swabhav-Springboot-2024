package com.aurionpro.connect.service;

import java.util.List;

import com.aurionpro.connect.entity.Payment;

public interface PaymentService {
	public List<Payment> getAllPayments();

	public Payment getPaymentById(int paymentId);

	public String addPayment(Payment payment);

	public String updatePayment(Payment payment);

	public String deletePayment(int paymentId);
}

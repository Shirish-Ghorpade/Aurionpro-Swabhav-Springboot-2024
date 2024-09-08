package com.aurionpro.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.connect.entity.Payment;
import com.aurionpro.connect.repository.PaymentRepository;


@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentRepository.getAllPayments();
	}

	@Override
	public Payment getPaymentById(int paymentID) {
		// TODO Auto-generated method stub
		return paymentRepository.getPaymentById(paymentID);
	}

	@Override
	public void addPayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepository.addPayment(payment);
	}

	@Override
	public void updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepository.updatePayment(payment);
	}

	@Override
	public void deletePayment(int paymentID) {
		// TODO Auto-generated method stub
		paymentRepository.deletePayment(paymentID);
	}

}

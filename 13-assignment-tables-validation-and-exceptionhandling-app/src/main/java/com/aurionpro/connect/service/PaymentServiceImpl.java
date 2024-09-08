package com.aurionpro.connect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.connect.entity.Payment;
import com.aurionpro.connect.exceptions.PaymentNotFoundException;
import com.aurionpro.connect.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentById(int paymentID) {

		Optional<Payment> dbPayments = paymentRepository.findById(paymentID);
		if (!dbPayments.isPresent()) {
			throw new PaymentNotFoundException(paymentID);
		}
		return dbPayments.get();
	}

	@Override
	public String addPayment(Payment payment) {
		paymentRepository.save(payment);
		return "payment is added sucessfully";
	}

	@Override
	public String updatePayment(Payment payment) {
		paymentRepository.save(payment);
		return "payment is updated sucessfully";
	}

	@Override
	public String deletePayment(int paymentID) {
		if (!paymentRepository.existsById(paymentID))
			throw new PaymentNotFoundException(paymentID);
		paymentRepository.deleteById(paymentID);
		return "payment is deleted sucessfully";
	}

}

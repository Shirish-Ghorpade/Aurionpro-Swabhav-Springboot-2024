package com.aurionpro.connect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.connect.entity.Payment;
import com.aurionpro.connect.service.PaymentService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/paymentapp")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/payment")
	public List<Payment> getAllPayment() {
		return paymentService.getAllPayments();
	}

	@GetMapping("/payment/{id}")
	public Payment getPaymentById(@PathVariable int id) {
		return paymentService.getPaymentById(id);
	}

	@Transactional
	@PostMapping("/payment")
	public void addPayment(@Valid @RequestBody Payment payment) {
		paymentService.addPayment(payment);
	}

	@Transactional
	@PutMapping("/payment/{id}")
	public void updatePayment(@PathVariable int id, @Valid @RequestBody Payment payment) {
		paymentService.updatePayment(payment);
	}

	@Transactional
	@DeleteMapping("/payment/{id}")
	public void deletePayment(@PathVariable int id) {
		paymentService.deletePayment(id);
	}
}

package com.aurionpro.connect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aurionpro.connect.entity.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Component
public class PaymentRespositoryImpl implements PaymentRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub

		TypedQuery<Payment> query = entityManager.createQuery("select p Payment from Payment p", Payment.class);
		return query.getResultList();
	}

	@Override
	public Payment getPaymentById(int paymentId) {
		// TODO Auto-generated method stub
		return entityManager.find(Payment.class, paymentId);
	}

	@Override
	public void addPayment(Payment payment) {
		// TODO Auto-generated method stub
		if (payment.getPaymentId() == 0) {
			entityManager.persist(payment);
			return;
		}
		entityManager.merge(payment);
	}

	@Override
	public void updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		if (payment.getPaymentId() == 0) {
			entityManager.persist(payment);
			return;
		}
		entityManager.merge(payment);
	}

	@Override
	public void deletePayment(int paymentId) {
		// TODO Auto-generated method stub
		Payment payment = entityManager.find(Payment.class, paymentId);
		if (payment != null) {
			entityManager.remove(payment);
		}

	}
}

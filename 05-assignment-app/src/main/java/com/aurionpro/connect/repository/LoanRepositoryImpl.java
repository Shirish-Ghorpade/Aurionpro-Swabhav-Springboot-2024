package com.aurionpro.connect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aurionpro.connect.entity.Loan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Component
public class LoanRepositoryImpl implements LoanRepository {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub

		TypedQuery<Loan> query = entityManager.createQuery("select l Loan from Loan l", Loan.class);
		return query.getResultList();
	}

	@Override
	public Loan getLoanById(int loanId) {
		// TODO Auto-generated method stub
		return entityManager.find(Loan.class, loanId);
	}

	@Override
	public void addLoan(Loan loan) {
		// TODO Auto-generated method stub
		if (loan.getLoanId() == 0) {
			entityManager.persist(loan);
			return;
		}
		entityManager.merge(loan);
	}

	@Override
	public void updateLoan(Loan loan) {
		// TODO Auto-generated method stub
		if (loan.getLoanId() == 0) {
			entityManager.persist(loan);
			return;
		}
		entityManager.merge(loan);
	}

	@Override
	public void deleteLoan(int loanId) {
		// TODO Auto-generated method stub
		Loan loan = entityManager.find(Loan.class, loanId);
		if (loan != null) {
			entityManager.remove(loan);
		}

	}

}

package com.aurionpro.connect.repository;

import java.util.List;

import com.aurionpro.connect.entity.Loan;

public interface LoanRepository {
	public List<Loan> getAllLoans();

	public Loan getLoanById(int loanId);

	public void addLoan(Loan loan);

	void updateLoan(Loan loan);

	public void deleteLoan(int loanId);
}

package com.aurionpro.connect.service;

import java.util.List;

import com.aurionpro.connect.entity.Loan;

public interface LoanService {
	
	
	public List<Loan> getAllLoans();

	public Loan getLoanById(int loanId);

	public void addLoan(Loan loan);

	void updateLoan(Loan loan);

	public void deleteLoan(int id);
	
}

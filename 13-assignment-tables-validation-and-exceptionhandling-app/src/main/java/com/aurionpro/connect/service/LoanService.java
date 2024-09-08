package com.aurionpro.connect.service;

import java.util.List;

import com.aurionpro.connect.entity.Loan;

public interface LoanService {
	
	public List<Loan> getAllLoans();

	public Loan getLoanById(int loanId);

	public String addLoan(Loan loan);

	public String updateLoan(int loanId, Loan loan);

	public String deleteLoan(int id);

}

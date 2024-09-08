package com.aurionpro.connect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.connect.entity.Loan;
import com.aurionpro.connect.exceptions.LoanNotFoundException;
import com.aurionpro.connect.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public List<Loan> getAllLoans() {
		return loanRepository.findAll();
	}

	@Override
	public Loan getLoanById(int loanID) {
		Optional<Loan> dbLoan = loanRepository.findById(loanID);
		if (!dbLoan.isPresent()) {
			throw new LoanNotFoundException(loanID);
		}
		return dbLoan.get();
	}

	@Override
	public String addLoan(Loan loan) {
		loanRepository.save(loan);
		return "Loan is added sucessfully";
	}

	@Override
	public String updateLoan(int loanID, Loan loan) {
		Optional<Loan> dbLoan = loanRepository.findById(loanID);
		if (!dbLoan.isPresent()) {
			throw new LoanNotFoundException(loanID);
		}
		Loan newLoan = dbLoan.get();
		newLoan.setLoanAmount(loan.getLoanAmount());
		newLoan.setInterestRate(loan.getInterestRate());
		newLoan.setStartDate(loan.getStartDate());
		newLoan.setEndDate(loan.getEndDate());
		newLoan.setLoanTerm(loan.getLoanTerm());
		newLoan.setLoanStatus(loan.getLoanStatus());
		loanRepository.save(newLoan);
		return "Loan is updated sucessfully";
	}

	@Override
	public String deleteLoan(int loanID) {
		if (!loanRepository.existsById(loanID))
			throw new LoanNotFoundException(loanID);
		loanRepository.deleteById(loanID);
		return "Loan is deleted sucessfully";
	}
}

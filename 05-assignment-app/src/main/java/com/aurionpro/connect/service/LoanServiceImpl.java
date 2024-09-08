package com.aurionpro.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.connect.entity.Loan;
import com.aurionpro.connect.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService{

		@Autowired
		private LoanRepository loanRepository;

		@Override
		public List<Loan> getAllLoans() {
			// TODO Auto-generated method stub
			return loanRepository.getAllLoans();
		}

		@Override
		public Loan getLoanById(int loanID) {
			// TODO Auto-generated method stub
			return loanRepository.getLoanById(loanID);
		}

		@Override
		public void addLoan(Loan loan) {
			// TODO Auto-generated method stub
			loanRepository.addLoan(loan);
		}

		@Override
		public void updateLoan(Loan loan) {
			// TODO Auto-generated method stub
			loanRepository.updateLoan(loan);
		}

		@Override
		public void deleteLoan(int loanID) {
			// TODO Auto-generated method stub
			loanRepository.deleteLoan(loanID);
		}

}

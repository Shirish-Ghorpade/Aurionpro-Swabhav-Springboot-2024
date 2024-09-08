package com.aurionpro.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.connect.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer>{
	
}

package com.aurionpro.connect.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loans")
public class Loan {
	@Column(name = "loanId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	@Column(name = "Loan Amount")
	private double loanAmount;
	@Column(name = "Interest Date")
	private double interestRate;
	@Column(name = "Start Date")
	private LocalDate startDate;
	@Column(name = "End Date")
	private LocalDate endDate;
	@Column(name = "Loan Status")
	private LoanStatus loanStatus;

	public Loan() {

	}

	public Loan(int loanId, double loanAmount, double interestRate, LocalDate startDate, LocalDate endDate,
			LoanStatus loanStatus) {
		super();
		this.loanId = loanId;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.loanStatus = loanStatus;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

}

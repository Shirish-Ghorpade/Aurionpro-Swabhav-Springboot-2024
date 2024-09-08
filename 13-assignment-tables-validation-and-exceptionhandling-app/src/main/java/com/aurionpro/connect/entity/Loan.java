package com.aurionpro.connect.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "loans")
public class Loan {
	@Column(name = "loanId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	@Column(name = "Loan Amount")
	@NotNull(message = "Loan amount is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Loan amount must be greater than 0")
	private double loanAmount;

	@Column(name = "Interest Date")
	@NotNull(message = "Interest is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Loan amount must be greater than 0")
	private double interestRate;
	
	@Column(name = "Loan Term")
	@NotNull(message = "Loan term is required")
	@Enumerated(EnumType.STRING)
	private LoanTerm loanTerm;

	@Column(name = "Start Date")
	@NotNull(message = "Start date is required")
	private LocalDate startDate;

	@Column(name = "End Date")
	@NotNull(message = "End date is required")
	@Future(message = "End date must be in future")
	private LocalDate endDate;

	@Column(name = "Loan Status is required")
	@NotNull(message = "Loan status is required")
	@Enumerated(EnumType.STRING)
	private LoanStatus loanStatus;
}

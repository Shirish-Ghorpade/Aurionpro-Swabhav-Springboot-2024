package com.aurionpro.mapping.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SalaryTransactions")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SalaryTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TransactionId")
	private int transactionId;

	@Column(name = "TransactionDate")
	private LocalDate transactionDate;

	@Column(name = "Amount")
	private double amount;

	@Column(name = "SalaryStatus")
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "AccountId")
	private SalaryAccount salaryAccount;

	@OneToOne
	@JoinColumn(name = "SalaryId")
	private Salary salary;
}

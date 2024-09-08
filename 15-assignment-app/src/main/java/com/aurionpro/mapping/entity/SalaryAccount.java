package com.aurionpro.mapping.entity;

import java.util.List;

import com.aurionpro.mapping.repository.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Table(name = "SalaryAccount")
public class SalaryAccount {
	@Column(name="accountId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	
	@Column(name = "accountNumber")
	private Long accountNumber;

	@Column(name = "accountHolderName")
	@NotBlank(message = "Account holder name is mandatory and cannot be blank")
	@Pattern(regexp = "^[A-Za-z]+$", message = "account holder name must contain only letters")
	private String accountHolderName;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "bankId")
	private Bank bank;

	@OneToMany(mappedBy = "salaryAccount")
	@JsonIgnore
	private List<SalaryTransaction> salaryTransaction;

}

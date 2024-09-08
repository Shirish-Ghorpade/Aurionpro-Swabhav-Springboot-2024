package com.aurionpro.mapping.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employeeId")
	private int employeeId;

	@Column(name = "firstName")
	@NotBlank(message = "First name is mandatory")
	@Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters")
	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	private String firstName;

	@Column(name = "lastName")
	@NotBlank(message = "Last name is mandatory")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters")
	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	private String lastName;

	@Column(name = "phoneNumber")
	@NotBlank(message = "Phone number is mandatory")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number. Must be 10 digits")
	private String phoneNumber;

	@Column(name = "email")
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid email format")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	@Size(min = 5, max = 200, message = "Email must be between 5 and 200 characters")
	private String email;

	@Column(name = "position")
	@NotNull(message = "Position is mandatory")
	@Enumerated(EnumType.STRING)
	private Position position;

	@Column(name = "hiredate")
	@NotNull(message = "Hire date is mandatory")
	@PastOrPresent(message = "Hire date must be in the past or present")
	private LocalDate hiredate;

	@Column(name = "salary")
	@DecimalMin(value = "0.01", inclusive = true, message = "Salary must be greater than 0")
	private double salary;

	@Column(name = "status")
	@NotNull(message = "Status is mandatory")
	@Enumerated(EnumType.STRING)
	private StatusEmployee status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accountId")
	private SalaryAccount salaryAccount;

	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "clientId")
	private Client client;
}

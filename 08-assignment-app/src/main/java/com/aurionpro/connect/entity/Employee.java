package com.aurionpro.connect.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
	@Column(name = "employeeId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "phoneNumber")
	private String phoneNumber;
	@Column(name = "contactPerson")
	private String contactPerson;
	@Column(name = "email")
	private String email;
	@Column(name = "position")
	@Enumerated(EnumType.STRING)
	private Position position;
	@Column(name = "hiredate")
	private LocalDate hiredate;

	@Column(name = "salary")
	private double salary;

	@Column(name = "bankAccountNumber")
	private long bankAccountNumber;

	@Column(name = "bankIFSCNumber")
	private String bankIFSCNumber;

	@Enumerated(EnumType.STRING)
	private StatusEmployee status;

}

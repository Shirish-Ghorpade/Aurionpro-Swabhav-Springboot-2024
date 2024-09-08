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

public class Client {
	@Column(name = "clientId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	@Column(name = "companyName")
	private String companyName;
	@Column(name = "registractionNumber")
	private String registrationNumber;
	@Column(name = "contactPerson")
	private String contactPerson;
	@Column(name = "contactEmail")
	private String contactEmail;
	@Column(name = "contactNumber")
	private String contactNumber;
	@Column(name = "address")
	private String address;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	@Enumerated(EnumType.STRING)
	@Column(name = "kycStatus")
	private KYCStatus kycStatus;
	@Column(name = "creationDate")
	private LocalDate creationDate;

}

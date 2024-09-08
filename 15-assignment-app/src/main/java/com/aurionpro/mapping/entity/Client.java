package com.aurionpro.mapping.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clientId")
	private int clientId;

	@Column(name = "companyName")
	@NotBlank(message = "Company name is mandatory")
	@Pattern(regexp = "^[A-Za-z\\s]*$", message = "Company name can only contain letters and spaces")
	@Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
	private String companyName;

	@Column(name = "registrationNumber")
	@NotBlank(message = "Registration number is mandatory")
	@Pattern(regexp = "^[A-Za-z0-9]{5,20}$", message = "Registration number must be alphanumeric and between 5 and 20 characters")
	private String registrationNumber;

	@Column(name = "contactPerson")
	@NotBlank(message = "Contact person is mandatory")
	@Size(min = 2, max = 50, message = "Contact person name must be between 2 and 50 characters")
	private String contactPerson;

	@Column(name = "contactEmail")
	@Email(message = "Invalid email address")
	@NotBlank(message = "Contact email is mandatory")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	private String contactEmail;

	@Column(name = "contactNumber")
	@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Contact number must be between 10 and 15 digits and may start with a '+'")
	private String contactNumber;

	@Column(name = "address")
	@NotBlank
	@Size(max = 255, message = "Address must be less than 255 characters")
	private String address;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is mandatory")
	private ClientStatus status;

	@Enumerated(EnumType.STRING)
	@Column(name = "kycStatus")
	@NotNull(message = "KYC status is mandatory")
	private KYCStatus kycStatus;

	@Column(name = "creationDate")
	@NotNull(message = "Creation date is mandatory")
	private LocalDate creationDate;

	@OneToMany(mappedBy = "client")
	private List<Employee> employees;

}
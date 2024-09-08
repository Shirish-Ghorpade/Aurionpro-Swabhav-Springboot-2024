package com.aurionpro.connect.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
@Table(name = "customers")
public class Customer {

	@Column(name = "customerID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerID;
	
	@Column(name = "firstName")
	@NotNull
	@Size(min=1, max=50)
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
	private String firstName;
	
	@Column(name = "lastName")
	@Size(min=1, max=50)
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
	private String lastName;
	
	@Column(name = "Date Of Birth")
	@NotNull
	@Past(message="Date of birth must be a past date")
	private LocalDate dateOfBirth;
	
	@NotNull
	@Email
	@Size(max=200)
	@Column(name = "Email Id")
	private String emailId;
	
	
	@NotNull
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
	@Column(name = "Mobile Number")
	private String mobileNumber;

}

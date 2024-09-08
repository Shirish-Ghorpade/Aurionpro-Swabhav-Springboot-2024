package com.aurionpro.jwtsecured.entity;

import org.hibernate.internal.build.AllowPrintStacktrace;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllowPrintStacktrace
@Data

@Table(name="customers")
public class Customer {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private long mobileNumber;
	

}

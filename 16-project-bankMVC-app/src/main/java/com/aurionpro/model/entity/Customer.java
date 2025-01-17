package com.aurionpro.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerId")
	private Long customerId;

	@Pattern(regexp = "^[A-Za-z ,.'-]+{2,30}$", message = "First name must contain only letters.")
	@Column(name = "firstName")
	private String firstName;

	@Pattern(regexp = "^[A-Za-z ,.'-]+{2,30}$", message = "Last name must contain only letters.")
	@Column(name = "lastName")
	private String lastName;

	@NotNull(message = "Email is mandatory.")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Not Valid format of Mail Id. Please enter the valid Mail Id")
	@Column(name = "email", nullable = false)
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User user;
	
	@Column(nullable = false)
	private boolean inactive = false;
	

	@OneToMany(mappedBy = "customer", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<BankAccount> bankAccounts;

	@OneToMany(mappedBy = "customer", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<Document> documents;
	
	

}

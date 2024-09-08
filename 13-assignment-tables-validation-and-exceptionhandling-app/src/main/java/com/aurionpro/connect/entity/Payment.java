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
import jakarta.validation.constraints.FutureOrPresent;
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
@Table(name = "payments")
public class Payment {

	@Column(name = "paymentId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;

	@Column(name = "Payment Date")
	@NotNull(message = "payment date is required")
	@FutureOrPresent(message = "Payment date cannot be in the past")
	private LocalDate paymentDate;

	@Column(name = "Amount")
	@NotNull(message = "Amount is required")
	@DecimalMin(value="0.0", inclusive=false, message = "Amount must be greater than 0.0")
	private Double amount;

	@Column(name = "Payment Mode")
	@NotNull(message = "Payment mode is required")
	@Enumerated(EnumType.STRING)
	private PaymentMode paymentMode;

	@Column(name = "Payment Status")
	@NotNull(message = "Payment status is required")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

}

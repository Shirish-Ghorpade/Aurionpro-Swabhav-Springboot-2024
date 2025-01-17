package com.aurionpro.model.dto;


import java.math.BigDecimal;

import com.aurionpro.model.entity.TransactionType;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDto {
	
	TransactionType transactionType;

	@DecimalMin(value = "0.0", message = "Balance must positive")
	@Column(name = "balance")
	private BigDecimal amount;
	
	private String senderAccountNumber;
	
	private String receiverAccountNumber;
}

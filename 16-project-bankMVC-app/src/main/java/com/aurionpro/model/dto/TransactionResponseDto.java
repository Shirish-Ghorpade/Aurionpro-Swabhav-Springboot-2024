package com.aurionpro.model.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.aurionpro.model.entity.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionResponseDto {

	private TransactionType transactionType;

	private BigDecimal amount;

	private String senderAccountNumber;

	private String receiverAccountNumber;

	private LocalDateTime date;

}

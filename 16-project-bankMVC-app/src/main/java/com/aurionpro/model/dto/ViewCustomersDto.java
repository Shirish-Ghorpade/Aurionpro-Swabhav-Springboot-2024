package com.aurionpro.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewCustomersDto {
	private Long customerId;
	private String firstName;
	private String lastName;
	private String AccountNumber;
	private BigDecimal balance;
}

package com.aurionpro.model.dto;

import com.aurionpro.model.entity.KycStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class DocumentRequestDto {
//	private int customerId;
//
//	private long documentId;

	private KycStatus kycStatus;
}

package com.aurionpro.model.dto;

import com.aurionpro.model.entity.DocumentType;
import com.aurionpro.model.entity.KycStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentResponseDto {
	private String documentName;

	private String documentUrl;

	KycStatus kycStatus;

	DocumentType documentType;
}

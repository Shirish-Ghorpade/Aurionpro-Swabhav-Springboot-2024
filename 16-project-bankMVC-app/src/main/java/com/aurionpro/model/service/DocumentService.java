package com.aurionpro.model.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.aurionpro.model.dto.DocumentDto;
import com.aurionpro.model.dto.DocumentRequestDto;
import com.aurionpro.model.dto.DocumentResponseDto;
import com.aurionpro.model.dto.PageResponse;

public interface DocumentService {
	ResponseEntity<Map> uploadDocument(DocumentDto documentDto);
	
	PageResponse<DocumentResponseDto> getAllDocumentsOfCustomer(Long customerId, int pageNo, int pageSize, String sortBy, String sortDir);

	DocumentResponseDto verifyCustomerDocument(Long customerId, long documentId, DocumentRequestDto documentRequestDto);
}

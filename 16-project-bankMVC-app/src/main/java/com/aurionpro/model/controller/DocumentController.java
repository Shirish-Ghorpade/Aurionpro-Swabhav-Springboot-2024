package com.aurionpro.model.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.model.dto.DocumentDto;
import com.aurionpro.model.dto.DocumentRequestDto;
import com.aurionpro.model.dto.DocumentResponseDto;
import com.aurionpro.model.dto.PageResponse;
import com.aurionpro.model.dto.ViewCustomersDto;
import com.aurionpro.model.respository.DocumentRepository;
import com.aurionpro.model.service.DocumentService;

@RestController
@RequestMapping("/bankapp")
public class DocumentController {
	
	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private DocumentService documentService;

	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/customer/upload")
	public ResponseEntity<Map> upload(DocumentDto documentDto) {
		try {
			return documentService.uploadDocument(documentDto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	@GetMapping("/customer/{customerId}/documents")
	ResponseEntity<PageResponse<DocumentResponseDto>> viewAllDocumentsOfCustomer(
			@PathVariable Long customerId,
			@RequestParam(defaultValue = "0", required = false) int pageNo,
			@RequestParam(defaultValue = "5", required = false) int pageSize,
			@RequestParam(defaultValue = "documentId", required = false) String sortBy,
			@RequestParam(defaultValue = "asc", required = false) String sortDir) {
		return ResponseEntity.ok(documentService.getAllDocumentsOfCustomer(customerId, pageNo, pageSize, sortBy, sortDir));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/admin/customer/{customerId}/documents/{documentId}/verify")
	ResponseEntity<DocumentResponseDto> verifyDocuments(@PathVariable Long customerId, @PathVariable Long documentId,
			@RequestBody DocumentRequestDto documentRequestDto) {
		return ResponseEntity.ok(documentService.verifyCustomerDocument(customerId, documentId, documentRequestDto));
	}
}
package com.aurionpro.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aurionpro.model.dto.DocumentDto;
import com.aurionpro.model.dto.DocumentRequestDto;
import com.aurionpro.model.dto.DocumentResponseDto;
import com.aurionpro.model.dto.PageResponse;
import com.aurionpro.model.entity.Customer;
import com.aurionpro.model.entity.Document;
import com.aurionpro.model.entity.DocumentType;
import com.aurionpro.model.entity.KycStatus;
import com.aurionpro.model.exceptions.NotFoundException;
import com.aurionpro.model.respository.CustomerRepository;
import com.aurionpro.model.respository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

	private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public ResponseEntity<Map> uploadDocument(DocumentDto documentDto) {
		try {
			if (documentDto.getName().isEmpty()) {
				return ResponseEntity.badRequest().build();
			}
			if (documentDto.getFile().isEmpty()) {
				return ResponseEntity.badRequest().build();
			}
			Long customerId = documentDto.getCustomerId();
			Customer customer = customerRepository.findById(customerId).orElseThrow(
					() -> new NotFoundException(customerId, "Customer is not found with the customer Id : "));

			Document document = new Document();
			document.setDocumentName(documentDto.getName());
			document.setDocumentUrl(cloudinaryService.uploadFile(documentDto.getFile(), "folder_1"));
			if (document.getDocumentUrl() == null) {
				return ResponseEntity.badRequest().build();
			}

			document.setKycStatus(KycStatus.PENDING);
			document.setCustomer(customer);
			document.setDocumentType(DocumentType.PROOF_OF_IDENTITY);
			documentRepository.save(document);
			return ResponseEntity.ok().body(Map.of("url", document.getDocumentUrl()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DocumentResponseDto verifyCustomerDocument(Long customerId, long documentId,  DocumentRequestDto documentRequestDto) {

		KycStatus statusUpdatedByAdmin = documentRequestDto.getKycStatus();

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new NotFoundException(customerId, "Customer is not found with the customer Id : "));

		List<Document> listOfCustomerDocuments = documentRepository.findByCustomer(customer);

		Document selectedDocument = null;
		for (Document document : listOfCustomerDocuments) {
			if (document.getDocumentId() == documentId) {
				selectedDocument = document;
			}
		}

		if (selectedDocument == null) {
			throw new NotFoundException(documentId, "Document is not found with id : ");
		}

		selectedDocument.setKycStatus(statusUpdatedByAdmin);
		documentRepository.save(selectedDocument);

		return toDocumentResponseDto(selectedDocument);
	}

	private DocumentResponseDto toDocumentResponseDto(Document document) {
		DocumentResponseDto documentResponseDto = new DocumentResponseDto();
		documentResponseDto.setDocumentName(document.getDocumentName());
		documentResponseDto.setDocumentType(document.getDocumentType());
		documentResponseDto.setDocumentUrl(document.getDocumentUrl());
		documentResponseDto.setKycStatus(document.getKycStatus());
		return documentResponseDto;
	}

	@Override
	public PageResponse<DocumentResponseDto> getAllDocumentsOfCustomer(Long customerId, int pageNo, int pageSize, String sortBy,
			String sortDir) {
		logger.info("Fetching all document by custom id with pagination - CustomerId:{}, Page: {}, Size: {}, SortBy: {}, SortDir: {}", pageNo,
				pageSize, sortBy, sortDir);
		
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new NotFoundException(customerId, "Customer is not found with the customer Id : "));

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Document> dbDocumentPage = documentRepository.findByCustomer(customer, pageable);

		PageResponse<DocumentResponseDto> documentResponseDtoPageResponse = new PageResponse<>();
		documentResponseDtoPageResponse.setTotalElements(pageSize);
		documentResponseDtoPageResponse.setSize(dbDocumentPage.getSize());
		documentResponseDtoPageResponse.setTotalElements(dbDocumentPage.getTotalElements());
		documentResponseDtoPageResponse.setLastPage(dbDocumentPage.isLast());

		List<DocumentResponseDto> documentResponseDtos = new ArrayList<>();
		dbDocumentPage.getContent().forEach(document -> {
			documentResponseDtos.add(toDocumentResponseDto(document));

		});
		documentResponseDtoPageResponse.setContent(documentResponseDtos);
		logger.info("Fetched {} documents", documentResponseDtos.size());

		return documentResponseDtoPageResponse;
	}
}
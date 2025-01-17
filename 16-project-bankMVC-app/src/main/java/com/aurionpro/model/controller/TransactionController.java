package com.aurionpro.model.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.model.dto.PageResponse;
import com.aurionpro.model.dto.TransactionByAccountNumberRequestDto;
import com.aurionpro.model.dto.TransactionDto;
import com.aurionpro.model.dto.TransactionResponseDto;
//import com.aurionpro.model.service.PdfService;
import com.aurionpro.model.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")

public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
//	@Autowired
//	private JobLauncher jobLauncher;
//
//	@Autowired
//	private Job generatePdfJob;

//	@Autowired
//	private PdfService pdfService;

	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/customer/transaction")
	ResponseEntity<String> makeNewTransaction(@Valid @RequestBody TransactionDto transactionDto) {
		return ResponseEntity.ok(transactionService.makeNewTransaction(transactionDto));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/transactions")
	ResponseEntity<PageResponse<TransactionResponseDto>> viewTransactions(
			@RequestParam(defaultValue = "0", required = false) int pageNo,
			@RequestParam(defaultValue = "3", required = false) int pageSize,
			@RequestParam(defaultValue = "date", required = false) String sortBy,
			@RequestParam(defaultValue = "desc", required = false) String sortDir) {
		return ResponseEntity.ok(transactionService.getAllTransactions(pageNo, pageSize, sortBy, sortDir));
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/customer/transactions")
	ResponseEntity<PageResponse<TransactionResponseDto>> viewTransactionsByAccountNumber(
			@RequestBody TransactionByAccountNumberRequestDto transactionByAccountNumberRequestDto,
			@RequestParam(defaultValue = "0", required = false) int pageNo,
			@RequestParam(defaultValue = "3", required = false) int pageSize,
			@RequestParam(defaultValue = "date", required = false) String sortBy,
			@RequestParam(defaultValue = "desc", required = false) String sortDir) {
		return ResponseEntity.ok(transactionService.getAllTransactionByAccountNumber(
				transactionByAccountNumberRequestDto, pageNo, pageSize, sortBy, sortDir));
	}

//	@GetMapping("/account/{accountId}/transactions/pdf/batch")
//	public String runBatchJob(@PathVariable Long accountId) throws Exception {
//		JobParameters jobParameters = new JobParametersBuilder().addLong("accountId", accountId).toJobParameters();
//		jobLauncher.run(generatePdfJob, jobParameters);
//		return "Batch job started for account ID " + accountId;
//	}
}
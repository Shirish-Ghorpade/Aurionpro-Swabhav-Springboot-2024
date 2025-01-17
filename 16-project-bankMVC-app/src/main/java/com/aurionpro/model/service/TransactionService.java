package com.aurionpro.model.service;

import com.aurionpro.model.dto.PageResponse;
import com.aurionpro.model.dto.TransactionByAccountNumberRequestDto;
import com.aurionpro.model.dto.TransactionDto;
import com.aurionpro.model.dto.TransactionResponseDto;



public interface TransactionService {
	
	String makeNewTransaction(TransactionDto transactionDto);

	PageResponse<TransactionResponseDto> getAllTransactions(int pageNo, int pageSize, String sortBy, String sortDir);
	
	PageResponse<TransactionResponseDto> getAllTransactionByAccountNumber(TransactionByAccountNumberRequestDto transactionByAccountNumberRequestDto, int pageNo, int pageSize, String sortBy, String sortDir);
	
//	PageResponse<TransactionResponseDto> getCreditDebitTransactionByCustomerId(int customerId, int pageNo, int pageSize, String sortBy, String sortDir);
	
}

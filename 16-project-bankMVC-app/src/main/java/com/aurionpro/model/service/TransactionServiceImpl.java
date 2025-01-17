package com.aurionpro.model.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aurionpro.model.dto.PageResponse;
import com.aurionpro.model.dto.TransactionByAccountNumberRequestDto;
import com.aurionpro.model.dto.TransactionDto;
import com.aurionpro.model.dto.TransactionResponseDto;
import com.aurionpro.model.entity.BankAccount;
import com.aurionpro.model.entity.Customer;
import com.aurionpro.model.entity.Transaction;
import com.aurionpro.model.entity.TransactionType;
import com.aurionpro.model.exceptions.InsufficientBalanceException;
import com.aurionpro.model.respository.BankAccountRepository;
import com.aurionpro.model.respository.CustomerRepository;
import com.aurionpro.model.respository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

	final BigDecimal MIN_BALANCE = new BigDecimal("5000.0");

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	EmailSender emailSender;

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Transactional
	public String credit(TransactionDto transactionDto) {
		String senderAccountNumber = transactionDto.getSenderAccountNumber();

		LocalDateTime date = LocalDateTime.now();

		BankAccount account = bankAccountRepository.findByAccountNumber(senderAccountNumber)
				.orElseThrow(() -> new RuntimeException("Account is not exist for Account Number: "+senderAccountNumber));

		Customer customer = account.getCustomer();

		BigDecimal amount = transactionDto.getAmount();

		Transaction creditTransaction = new Transaction();
		creditTransaction.setTransactionType(TransactionType.CREDIT);
		account.setBalance(account.getBalance().add(amount));
		creditTransaction.setAmount(amount);
		creditTransaction.setSenderAccount(account);
		creditTransaction.setReceiverAccount(null);
		creditTransaction.setDate(date);
		transactionRepository.save(creditTransaction);
		logger.info("Amount {} credited successfully from account {}", amount, account.getAccountNumber());

		// Construct the subject and body for the email
		String subject = "Success !!!!";
		String body = "Dear " + customer.getFirstName() + "In your account " + amount
				+ " is credited So, current balance is " + account.getBalance();

		// Call the sendEmail method
		String emailResult = emailSender.sendEmail(subject, body, customer.getEmail());

		// Log the result of the email sending process
		if ("Success".equals(emailResult)) {
			logger.info("Welcome email sent successfully to: {}", customer.getFirstName());
		} else {
			logger.error("Failed to send welcome email to {}: {}", customer.getFirstName(), emailResult);
		}
		return "Sucessfully credited " + amount + " in account number " + account.getAccountNumber();
	}

	@Transactional
	public String debit(TransactionDto transactionDto) {
		String senderAccontNumber = transactionDto.getSenderAccountNumber();

		LocalDateTime date = LocalDateTime.now();

		BankAccount account = bankAccountRepository.findByAccountNumber(senderAccontNumber).orElseThrow(
				() -> new RuntimeException("Account is not exist for Account Number: " + senderAccontNumber));

		Customer customer = account.getCustomer();

		BigDecimal amount = transactionDto.getAmount();

		if (account.getBalance().subtract(amount).compareTo(MIN_BALANCE) == -1) {
			logger.error("Insufficient balance in account {}. Current balance: {}, Required: {}",
					account.getAccountNumber(), account.getBalance(), amount);
			throw new InsufficientBalanceException(amount, account.getBalance());
		}
		Transaction debitTransaction = new Transaction();
		debitTransaction.setTransactionType(TransactionType.DEBIT);
		account.setBalance(account.getBalance().subtract(amount));
		debitTransaction.setAmount(amount);
		debitTransaction.setSenderAccount(account);
		debitTransaction.setReceiverAccount(null);
		debitTransaction.setDate(date);
		transactionRepository.save(debitTransaction);
		logger.info("Amount {} debited successfully from account {}", amount, account.getAccountNumber());

		// Construct the subject and body for the email
		String subject = "Success !!!!";
		String body = "Dear " + customer.getFirstName() + "In your account " + amount
				+ " is debited So, current balance is " + account.getBalance();

		// Call the sendEmail method
		String emailResult = emailSender.sendEmail(subject, body, customer.getEmail());

		// Log the result of the email sending process
		if ("Success".equals(emailResult)) {
			logger.info("Welcome email sent successfully to: {}", customer.getFirstName());
		} else {
			logger.error("Failed to send welcome email to {}: {}", customer.getFirstName(), emailResult);
		}

		return "Sucessfully debited " + amount + " in account number " + account.getAccountNumber();
	}

	@Transactional
	@Override
	public String makeNewTransaction(TransactionDto transactionDto) {

		TransactionType transactionType = transactionDto.getTransactionType();

		if (transactionType == TransactionType.CREDIT) {
			return credit(transactionDto);
		}

		if (transactionType == TransactionType.DEBIT) {
			return debit(transactionDto);
		}

		String senderAccNumber = transactionDto.getSenderAccountNumber();

		LocalDateTime date = LocalDateTime.now();

		BankAccount senderAccount = bankAccountRepository.findByAccountNumber(senderAccNumber)
				.orElseThrow(() -> new RuntimeException("Account is not exist for Account Number: " + senderAccNumber));

		Customer customer = senderAccount.getCustomer();

		String receiverAccNumber = transactionDto.getReceiverAccountNumber();

		BankAccount receiverAccount = bankAccountRepository.findByAccountNumber(receiverAccNumber).orElseThrow(
				() -> new RuntimeException("Account is not exist for Account Number: " + receiverAccNumber));

		BigDecimal amount = transactionDto.getAmount();

		if (senderAccNumber.equals(receiverAccNumber)) {
			logger.error("Cannot transfer to the same account. Sender and receiver account number: {}",
					receiverAccNumber);
			throw new RuntimeException("Cannot transfer money within the same account./r/n"
					+ "Sender and receiver account number cannot be the same.");
		}
		Transaction creditTransaction = new Transaction();
		creditTransaction.setTransactionType(TransactionType.CREDIT);
		receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
		creditTransaction.setAmount(amount);
		creditTransaction.setSenderAccount(senderAccount);
		creditTransaction.setReceiverAccount(receiverAccount);
		creditTransaction.setDate(date);
		transactionRepository.save(creditTransaction);

		Transaction debitTransaction = new Transaction();
		debitTransaction.setTransactionType(TransactionType.DEBIT);
		senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
		debitTransaction.setAmount(amount);
		debitTransaction.setSenderAccount(senderAccount);
		debitTransaction.setReceiverAccount(receiverAccount);
		debitTransaction.setDate(date);
		transactionRepository.save(debitTransaction);
		logger.info("Transaction successful: {} transferred from account {} to account {}", amount, senderAccNumber,
				receiverAccNumber);

		// Construct the subject and body for the email
		String subject = "Success !!!!";
		String body = "Dear " + customer.getFirstName() + amount + " is successfully transferred to "
				+ receiverAccNumber + " So, current balance is " + senderAccount.getBalance();

		// Call the sendEmail method
		String emailResult = emailSender.sendEmail(subject, body, customer.getEmail());

		// Log the result of the email sending process
		if ("Success".equals(emailResult)) {
			logger.info("Welcome email sent successfully to: {}", customer.getFirstName());
		} else {
			logger.error("Failed to send welcome email to {}: {}", customer.getFirstName(), emailResult);
		}

		return "Success !! " + amount + " is transfered sucessfully from " + senderAccNumber + " to "
				+ receiverAccNumber;
	}

	@Override
	public PageResponse<TransactionResponseDto> getAllTransactions(int pageNo, int pageSize, String sortBy,
			String sortDir) {
		logger.info("Fetching all transactions with pagination. Page: {}, Size: {}, SortBy: {}, SortDir: {}", pageNo,
				pageSize, sortBy, sortDir);

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Transaction> dbTransactionPage = transactionRepository.findAll(pageable);

		PageResponse<TransactionResponseDto> viewTransactionResponseDtoPageResponse = new PageResponse<>();
		viewTransactionResponseDtoPageResponse.setTotalElements(pageSize);
		viewTransactionResponseDtoPageResponse.setSize(dbTransactionPage.getSize());
		viewTransactionResponseDtoPageResponse.setTotalElements(dbTransactionPage.getTotalElements());
		viewTransactionResponseDtoPageResponse.setLastPage(dbTransactionPage.isLast());

		List<TransactionResponseDto> viewTransactionDtos = new ArrayList<>();
		dbTransactionPage.getContent().forEach(transaction -> {
			viewTransactionDtos.add(toTransactionResponseDto(transaction));
		});

		viewTransactionResponseDtoPageResponse.setContent(viewTransactionDtos);
		logger.info("Fetched {} transactions", viewTransactionDtos.size());

		return viewTransactionResponseDtoPageResponse;
	}

	@Override
	public PageResponse<TransactionResponseDto> getAllTransactionByAccountNumber(
			TransactionByAccountNumberRequestDto transactionByAccountNumberRequestDto, int pageNo, int pageSize,
			String sortBy, String sortDir) {

		String accountNumber = transactionByAccountNumberRequestDto.getAccountNumber();

		BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new RuntimeException("Account is not exist for Account Number: " + accountNumber));

		logger.info(
				"Fetching transactions for account number {} with pagination. Page: {}, Size: {}, SortBy: {}, SortDir: {}",
				accountNumber, pageNo, pageSize, sortBy, sortDir);

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Transaction> dbTransferPage = transactionRepository.findBySenderAccountOrReceiverAccount(account, account,
				pageable);

		PageResponse<TransactionResponseDto> viewTransactionResponseDtoPageResponse = new PageResponse<>();
		viewTransactionResponseDtoPageResponse.setTotalElements(pageSize);
		viewTransactionResponseDtoPageResponse.setSize(pageSize);
		viewTransactionResponseDtoPageResponse.setTotalElements(dbTransferPage.getTotalElements());
		viewTransactionResponseDtoPageResponse.setLastPage(dbTransferPage.isLast());

		List<TransactionResponseDto> viewTransactionResponseDtos = new ArrayList<>();
		dbTransferPage.getContent().forEach(transaction -> {
			viewTransactionResponseDtos.add(toTransactionResponseDto(transaction));
		});

		viewTransactionResponseDtoPageResponse.setContent(viewTransactionResponseDtos);
		logger.info("Fetched {} transactions for Account Number {}", viewTransactionResponseDtos.size(), accountNumber);

		return viewTransactionResponseDtoPageResponse;
	}

	public Transaction toTransactionMapper(TransactionDto transactionDto) {
		logger.info("Mapping TransactionDto to Transaction entity");
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDto.getAmount());
		return transaction;
	}

	public TransactionResponseDto toTransactionResponseDto(Transaction transaction) {
		logger.info("Mapping Transaction entity to TransactionResponseDto");
		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setTransactionType(transaction.getTransactionType());
		transactionResponseDto.setAmount(transaction.getAmount());
		transactionResponseDto.setDate(transaction.getDate());
		transactionResponseDto.setSenderAccountNumber(transaction.getSenderAccount().getAccountNumber());
		transactionResponseDto.setReceiverAccountNumber(
				transaction.getReceiverAccount() == null ? null : transaction.getReceiverAccount().getAccountNumber());
		return transactionResponseDto;
	}

}

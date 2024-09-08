package com.aurionpro.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.model.dto.BankAccountDto;
import com.aurionpro.model.dto.CustomerDto;
import com.aurionpro.model.dto.PageResponse;
import com.aurionpro.model.dto.TransactionDto;
import com.aurionpro.model.dto.ViewCustomerDto;
import com.aurionpro.model.entity.BankAccount;
import com.aurionpro.model.entity.Customer;
import com.aurionpro.model.respository.BankAccountRepository;
import com.aurionpro.model.respository.CustomerRepository;
import com.aurionpro.model.respository.TransactionRepository;

import jakarta.transaction.Transaction;

@Service
public class AdminServiceImpl implements AdminService {
	HashSet<Long> accountNumbers = new HashSet<>();

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public CustomerDto addNewCustomer(Customer customer) {
		return toCustomerDtoMapper(customerRepository.save(customer));
	}

	@Override
	public CustomerDto getCustomerById(int customerId) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (!optionalCustomer.isPresent()) {
			throw new NotFoundException(customerId, "Customer not found with id : ");
		}
		return toCustomerDtoMapper(optionalCustomer.get());
	}

	@Override
	public String deleteCustomerById(int customerId) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (!optionalCustomer.isPresent()) {
			throw new NotFoundException(customerId, "Customer not found with id : ");
		}
		customerRepository.deleteById(customerId);
		return "Customer Deleted Sucessfully";

	}

	@Override
	public CustomerDto updateCustomerById(int customerId, CustomerDto customerDto) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (!optionalCustomer.isPresent()) {
			throw new NotFoundException(customerId, "Customer not found with id : ");
		}
		Customer customer = optionalCustomer.get();
		customer.setCustomerId(customerId);
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
		return toCustomerDtoMapper(customerRepository.save(customer));
	}

	private Long generateAccountNumber() {
		long accountNumber = 0L;
		while (true) {
			long randomNumber = generateAccountNumberHelper();
			if (!accountNumbers.contains(randomNumber)) {
				accountNumber = randomNumber;
				break;
			}
		}
		return accountNumber;
	}

	private static long generateAccountNumberHelper() {
		Random random = new Random();
		return 10000000000L + (long) (random.nextDouble() * 9000000000L);
	}

	@Override
	public BankAccountDto addBankAccount(int customerId, double initialBalance) {

		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (!optionalCustomer.isPresent()) {
			throw new NotFoundException(customerId, "Customer not found which having id : ");
		}

		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(generateAccountNumber());
		bankAccount.setBalance(initialBalance);
		bankAccount.setCustomer(optionalCustomer.get());
		return toBankAccountDtoMapper(bankAccountRepository.save(bankAccount));
	}

	@Override
	public BankAccountDto getBankAccountByAccountId(int accountId) {
		Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountId);
		if (!optionalBankAccount.isPresent()) {
			throw new NotFoundException(accountId, "Account not found which having id : ");
		}
		return toBankAccountDtoMapper(optionalBankAccount.get());
	}

	@Override
	public String deleteBankAccountByAccountId(int accountId) {
		Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountId);
		if (!optionalBankAccount.isPresent()) {
			throw new NotFoundException(accountId, "Account not found which having id : ");
		}
		bankAccountRepository.deleteById(accountId);
		return "Account is sucessfully deleted";
	}

	@Override
	public BankAccountDto updateBankAccountByAccountId(int accountId, BankAccountDto bankAccountDto) {
		Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountId);
		if (!optionalBankAccount.isPresent()) {
			throw new NotFoundException(accountId, "Account not found which having id : ");
		}
		BankAccount bankAccount = optionalBankAccount.get();
		bankAccount.setAccountNumber(bankAccountDto.getAccountNumber());
		bankAccount.setBalance(bankAccountDto.getBalance());
		return toBankAccountDtoMapper(bankAccountRepository.save(bankAccount));
	}

	@Override
	public PageResponse<ViewCustomerDto> viewCustomers(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Customer> dbCustomerPage = customerRepository.findAll(pageable);

		PageResponse<ViewCustomerDto> viewCustomerDtoPageResponse = new PageResponse<>();
		viewCustomerDtoPageResponse.setTotalElements(pageSize);
		viewCustomerDtoPageResponse.setSize(dbCustomerPage.getSize());
		viewCustomerDtoPageResponse.setTotalElements(dbCustomerPage.getTotalElements());
//			Here it is not working so we use logic below
//			instructorPageResponse.setContent(instructorPage.getContent());
		viewCustomerDtoPageResponse.setLastPage(dbCustomerPage.isLast());

//			setContent logic
		List<ViewCustomerDto> viewCustomerDtos = new ArrayList<>();

		dbCustomerPage.getContent().forEach(customer -> {
			ViewCustomerDto viewCustomerDto = new ViewCustomerDto();
			viewCustomerDto.setFirstName(customer.getFirstName());
			viewCustomerDto.setLastName(customer.getLastName());
			viewCustomerDto.setAccountNumber(customer.getBankAccount().getAccountNumber());
			viewCustomerDto.setBalance(customer.getBankAccount().getBalance());

			viewCustomerDtos.add(viewCustomerDto);

		});
		viewCustomerDtoPageResponse.setContent(viewCustomerDtos);

		return viewCustomerDtoPageResponse;

	}

	@Override
	public PageResponse<TransactionDto> viewTransactions(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Transaction> dbTransactionPage = transactionRepository.findAll(pageable);

		PageResponse<TransactionDto> viewTransactionDtoPageResponse = new PageResponse<>();
		viewTransactionDtoPageResponse.setTotalElements(pageSize);
		viewTransactionDtoPageResponse.setSize(dbTransactionPage.getSize());
		viewTransactionDtoPageResponse.setTotalElements(dbTransactionPage.getTotalElements());
//			Here it is not working so we use logic below
//			instructorPageResponse.setContent(instructorPage.getContent());
		viewTransactionDtoPageResponse.setLastPage(dbTransactionPage.isLast());

//			setContent logic
		List<TransactionDto> transactionDtos = new ArrayList<>();

		dbTransactionPage.getContent().forEach(transaction -> {
			transactionDtos.add(toTransactionDtoMapper(transaction));

		});
		viewTransactionDtoPageResponse.setContent(transactionDtos);

		return viewTransactionDtoPageResponse;
	}

	public CustomerDto toCustomerDtoMapper(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPassword(customer.getPassword());
		return customerDto;
	}

	public Customer toCustomerMapper(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
		return customer;
	}

	public BankAccountDto toBankAccountDtoMapper(BankAccount bankAccount) {
		BankAccountDto bankAccountDto = new BankAccountDto();
		bankAccountDto.setAccountNumber(bankAccount.getAccountNumber());
		bankAccountDto.setBalance(bankAccount.getBalance());
		bankAccountDto.setAccountId(bankAccount.getAccountId());
		return bankAccountDto;
	}

	public BankAccount toBankAccountMapper(BankAccountDto bankAccountDto) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(bankAccountDto.getAccountNumber());
		bankAccount.setBalance(bankAccountDto.getBalance());
		bankAccount.setAccountId(bankAccount.getAccountId());
		return bankAccount;
	}

	public Transaction toTransactionMapper(TransactionDto transactionDto) {
		Transaction transaction = new Transaction();
		transaction.setTransactionType(transactionDto.getTransactionType());
		transaction.setAmount(transactionDto.getAmount());
		transaction.setDate(transactionDto.getDate());
		transaction.setSenderAccount(transactionDto.getSenderAccount());
		transaction.setReceiverAccount(transactionDto.getReceiverAccount());
		return transaction;
	}

	public TransactionDto toTransactionDtoMapper(Transaction transaction) {
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTransactionType(transaction.getTransactionType());
		transactionDto.setAmount(transaction.getAmount());
		transactionDto.setDate(transaction.getDate());
		transactionDto.setSenderAccount(transaction.getSenderAccount());
		transactionDto.setReceiverAccount(transaction.getReceiverAccount());
		transactionDto.setSenderAccountNumber(transaction.getSenderAccount().getAccountNumber());
		transactionDto.setReceiverAccountNumber(transaction.getReceiverAccount().getAccountNumber());
		return transactionDto;
	}

}

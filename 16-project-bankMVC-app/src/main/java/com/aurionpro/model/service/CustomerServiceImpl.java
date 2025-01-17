package com.aurionpro.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.model.dto.CustomerDataDto;
import com.aurionpro.model.dto.CustomerDto;
import com.aurionpro.model.dto.PageResponse;
import com.aurionpro.model.dto.RegistrationDto;
import com.aurionpro.model.dto.ViewCustomersDto;
import com.aurionpro.model.entity.BankAccount;
import com.aurionpro.model.entity.Customer;
import com.aurionpro.model.entity.User;
import com.aurionpro.model.exceptions.NotFoundException;
import com.aurionpro.model.exceptions.UserApiException;
import com.aurionpro.model.respository.CustomerRepository;
import com.aurionpro.model.respository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AuthService authService;

	@Autowired
	EmailSender emailSender;

	@Transactional
	@Override
	public CustomerDto addNewCustomer(CustomerDataDto customerDataDto) {
		logger.info("Adding a new customer: {}", customerDataDto.getUsername());

		RegistrationDto registrationDto = new RegistrationDto();
		registrationDto.setUsername(customerDataDto.getUsername());
		registrationDto.setPassword(customerDataDto.getPassword());
		registrationDto.setRoleName("ROLE_CUSTOMER");

		User user = authService.register(registrationDto);
		logger.info("User registered successfully for customer: {}", customerDataDto.getUsername());

		Customer customer = toCustomerMapper(customerDataDto);
		customer.setUser(user);
		CustomerDto savedCustomer = toCustomerDtoMapper(customerRepository.save(customer));

		logger.info("Customer saved successfully with ID: {}", savedCustomer.getCustomerId());

		// Construct the subject and body for the email
		String subject = "Welcome to Aurionpro - Your Financial Journey Begins Here!";
		String body = "Dear " + customerDataDto.getUsername()
				+ ", Welcome to the Aurionpro family! We are thrilled to have you as a valued customer and look forward to helping you achieve your financial goals.\r\n"
				+ "\r\n" + "Your default password is " + customerDataDto.getFirstName().substring(0, 3)
				+ "#123. Please recommended to changed password after login";

		// Call the sendEmail method
		String emailResult = emailSender.sendEmail(subject, body, customerDataDto.getEmail());

		// Log the result of the email sending process
		if ("Success".equals(emailResult)) {
			logger.info("Welcome email sent successfully to: {}", customerDataDto.getUsername());
		} else {
			logger.error("Failed to send welcome email to {}: {}", customerDataDto.getUsername(), emailResult);
		}

		return savedCustomer;
	}

	@Override
	public CustomerDto getCustomerById(Long customerId) {

		logger.info("Fetching customer by ID: {}", customerId);
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (!optionalCustomer.isPresent()) {
			logger.error("Customer not found with ID: {}", customerId);
			throw new NotFoundException(customerId, "Customer not found with id: ");
		}
		logger.info("Customer found with ID: {}", customerId);
		return toCustomerDtoMapper(optionalCustomer.get());
	}

	@Override
	public PageResponse<ViewCustomersDto> getAllCustomers(int pageNo, int pageSize, String sortBy, String sortDir) {
		logger.info("Fetching all customers with pagination - Page: {}, Size: {}, SortBy: {}, SortDir: {}", pageNo,
				pageSize, sortBy, sortDir);

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Customer> dbCustomerPage = customerRepository.findAll(pageable);

		PageResponse<ViewCustomersDto> viewCustomerDtoPageResponse = new PageResponse<>();
		viewCustomerDtoPageResponse.setTotalElements(pageSize);
		viewCustomerDtoPageResponse.setSize(dbCustomerPage.getSize());
		viewCustomerDtoPageResponse.setTotalElements(dbCustomerPage.getTotalElements());
		viewCustomerDtoPageResponse.setLastPage(dbCustomerPage.isLast());

		List<ViewCustomersDto> viewCustomerDtos = new ArrayList<>();
		dbCustomerPage.getContent().forEach(customer -> {
			ViewCustomersDto viewCustomerDto = new ViewCustomersDto();
			viewCustomerDto.setCustomerId(customer.getCustomerId());
			viewCustomerDto.setFirstName(customer.getFirstName());
			viewCustomerDto.setLastName(customer.getLastName());
			List<BankAccount> bankAccountsOfCustomer = customer.getBankAccounts();
			if (bankAccountsOfCustomer.size() != 0) {
				bankAccountsOfCustomer.forEach((bankAccount) -> {
					viewCustomerDto.setAccountNumber(bankAccount != null ? bankAccount.getAccountNumber() : null);
					viewCustomerDto.setBalance(bankAccount != null ? bankAccount.getBalance() : null);
				});
			}
			viewCustomerDtos.add(viewCustomerDto);
		});

		viewCustomerDtoPageResponse.setContent(viewCustomerDtos);
		logger.info("Fetched {} customers", viewCustomerDtos.size());

		return viewCustomerDtoPageResponse;
	}

//	@Override
//	public PageResponse<ViewCustomersDto> getAllCustomersSortedByBalance(int pageNo, int pageSize) {
//		logger.info("Fetching all customers sorted by balance with pagination - Page: {}, Size: {}", pageNo, pageSize);
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		Page<ViewCustomersDto> dbCustomerPage = customerRepository.getAllCustomersSortedByBalance(pageable);
//
//		PageResponse<ViewCustomersDto> viewCustomerDtoPageResponse = new PageResponse<>();
//		viewCustomerDtoPageResponse.setTotalElements(pageSize);
//		viewCustomerDtoPageResponse.setSize(dbCustomerPage.getSize());
//		viewCustomerDtoPageResponse.setTotalElements(dbCustomerPage.getTotalElements());
//		viewCustomerDtoPageResponse.setContent(dbCustomerPage.getContent());
//
//		logger.info("Fetched customers sorted by balance with total elements: {}", dbCustomerPage.getTotalElements());
//
//		return viewCustomerDtoPageResponse;
//	}
//
//	@Override
//	public PageResponse<ViewCustomersDto> getAllCustomersSortedByDescBalance(int pageNo, int pageSize) {
//		logger.info("Fetching all customers sorted by descending balance with pagination - Page: {}, Size: {}", pageNo,
//				pageSize);
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		Page<ViewCustomersDto> dbCustomerPage = customerRepository.getAllCustomersSortedByDescBalance(pageable);
//
//		PageResponse<ViewCustomersDto> viewCustomerDtoPageResponse = new PageResponse<>();
//		viewCustomerDtoPageResponse.setTotalElements(pageSize);
//		viewCustomerDtoPageResponse.setSize(dbCustomerPage.getSize());
//		viewCustomerDtoPageResponse.setTotalElements(dbCustomerPage.getTotalElements());
//		viewCustomerDtoPageResponse.setContent(dbCustomerPage.getContent());
//
//		logger.info("Fetched customers sorted by descending balance with total elements: {}",
//				dbCustomerPage.getTotalElements());
//
//		return viewCustomerDtoPageResponse;
//	}

	@Override
	public CustomerDto updateCustomerById(Long customerId, CustomerDataDto customerDataDto) {

		logger.info("Updating customer with ID: {}", customerId);
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (!optionalCustomer.isPresent()) {
			logger.error("Customer not found with ID: {}", customerId);
			throw new NotFoundException(customerId, "Customer not found with id: ");
		}
		if (userRepository.existsByUsername(customerDataDto.getUsername())) {
			logger.error("Updated failed: User already exists with username: {}", customerDataDto.getUsername());
			throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists");
		}
		Customer customer = optionalCustomer.get();
		customer.setFirstName(customerDataDto.getFirstName());
		customer.setLastName(customerDataDto.getLastName());
		customer.setEmail(customerDataDto.getEmail());

		User user = customer.getUser();
		user.setUsername(customerDataDto.getUsername());
		user.setPassword(passwordEncoder.encode(customerDataDto.getPassword()));
		userRepository.save(user);

		CustomerDto updatedCustomer = toCustomerDtoMapper(customerRepository.save(customer));
		logger.info("Customer updated successfully with ID: {}", updatedCustomer.getCustomerId());

		// Construct the subject and body for the email
		String subject = "Customer details is sucessfully updated";
		String body = "Dear " + customer.getFirstName() + " your customer details section is sucessfully updated";
		// Call the sendEmail method
		String emailResult = emailSender.sendEmail(subject, body, customer.getEmail());

		// Log the result of the email sending process
		if ("Success".equals(emailResult)) {
			logger.info("Welcome email sent successfully to: {}", customer.getFirstName());
		} else {
			logger.error("Failed to send welcome email to {}: {}", customer.getFirstName(), emailResult);
		}

		return updatedCustomer;
	}

	@Override
	public String deleteCustomerById(Long customerId) {
		logger.info("Deleting customer with ID: {}", customerId);
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new NotFoundException(customerId, "Customer not found with id: "));

		customer.setInactive(true);
		logger.info("Customer deleted successfully with ID: {}", customerId);

		return "Customer Deleted Successfully";
	}

	private CustomerDto toCustomerDtoMapper(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		return customerDto;
	}

	private Customer toCustomerMapper(CustomerDataDto customerDataDto) {
		Customer customer = new Customer();
		customer.setFirstName(customerDataDto.getFirstName());
		customer.setLastName(customerDataDto.getLastName());
		customer.setEmail(customerDataDto.getEmail());
		return customer;
	}
}

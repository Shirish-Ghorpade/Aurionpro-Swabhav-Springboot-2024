package com.aurionpro.connect.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.connect.entity.Customer;
import com.aurionpro.connect.exceptions.CustomerNotFoundException;
import com.aurionpro.connect.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(int customerID) {
		Optional<Customer> dbCustomer = customerRepository.findById(customerID);
		if (!dbCustomer.isPresent()) {
			logger.error("Error ");
			throw new CustomerNotFoundException(customerID);
		}
		return dbCustomer.get();
	}

	@Override
	public String addCustomer(Customer customer) {
		customerRepository.save(customer);
		logger.info("Added Sucessfully");
		return "Customer is added successfully";
	}

	@Override
	public String updateCustomer(int customerID, Customer customer) {
		Optional<Customer> dbCustomer = customerRepository.findById(customerID);
		if (!dbCustomer.isPresent()) {
			throw new CustomerNotFoundException(customerID);
		}
		Customer newCustomer = dbCustomer.get();
		newCustomer.setDateOfBirth(customer.getDateOfBirth());
		newCustomer.setEmailId(customer.getEmailId());
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setMobileNumber(customer.getMobileNumber());
		customerRepository.save(newCustomer);
		return "Customer is updated successfully";
	}

	@Override
	public String deleteCustomer(int customerID) {
		if (!customerRepository.existsById(customerID))
			throw new CustomerNotFoundException(customerID);

		customerRepository.deleteById(customerID);
		return "Customer is deleted successfully";
	}


}

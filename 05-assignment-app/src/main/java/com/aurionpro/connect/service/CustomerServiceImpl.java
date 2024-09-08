package com.aurionpro.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.connect.entity.Customer;
import com.aurionpro.connect.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.getAllCustomers();
	}

	@Override
	public Customer getCustomerById(int customerID) {
		// TODO Auto-generated method stub
		return customerRepository.getCustomerById(customerID);
	}

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.addCustomer(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.updateCustomer(customer);
	}

	@Override
	public void deleteCustomer(int customerID) {
		// TODO Auto-generated method stub
		customerRepository.deleteCustomer(customerID);
	}

}

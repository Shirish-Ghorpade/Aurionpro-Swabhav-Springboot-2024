package com.aurionpro.jwtsecured.service;

import java.util.List;

import com.aurionpro.jwtsecured.entity.Customer;

public interface CustomerService {
	
	List<Customer> getAllCustomer();
	
	Customer getCustomerById(int customerId);
}

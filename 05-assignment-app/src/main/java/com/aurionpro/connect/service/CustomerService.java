package com.aurionpro.connect.service;

import java.util.List;

import com.aurionpro.connect.entity.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();

	public Customer getCustomerById(int customerID);

	public void addCustomer(Customer customer);

	void updateCustomer(Customer customer);

	public void deleteCustomer(int customerID);
}

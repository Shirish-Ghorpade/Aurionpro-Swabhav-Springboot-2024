package com.aurionpro.connect.repository;

import java.util.List;

import com.aurionpro.connect.entity.Customer;

public interface CustomerRepository {
	public List<Customer> getAllCustomers();

	public Customer getCustomerById(int customerID);

	public void addCustomer(Customer customer);

	void updateCustomer(Customer customer);

	public void deleteCustomer(int customerID);

}

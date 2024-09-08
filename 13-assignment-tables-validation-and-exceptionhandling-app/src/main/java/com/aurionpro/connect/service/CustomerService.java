package com.aurionpro.connect.service;

import java.util.List;

import com.aurionpro.connect.entity.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();

	public Customer getCustomerById(int customerID);

	public String addCustomer(Customer customer);

	public String deleteCustomer(int customerID);

	String updateCustomer(int customerID, Customer customer);
}

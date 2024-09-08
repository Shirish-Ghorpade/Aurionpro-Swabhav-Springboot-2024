package com.aurionpro.connect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.connect.entity.Customer;
import com.aurionpro.connect.service.CustomerService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/customerapp")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer")
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable int id) {
		return customerService.getCustomerById(id);
	}

	@Transactional
	@PostMapping("/customer")
	public void addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
	}

	@Transactional
	@PutMapping("/{id}")
	public void updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		customerService.updateCustomer(customer);
	}

	@Transactional
	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable int id) {
		customerService.deleteCustomer(id);
	}
}

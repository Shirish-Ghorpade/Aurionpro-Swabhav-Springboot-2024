package com.aurionpro.jwtsecured.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.jwtsecured.entity.Customer;
import com.aurionpro.jwtsecured.service.CustomerService;


@RestController
@RequestMapping("/app")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/customer/{customerId}")
	ResponseEntity<Customer> getCustomerById(@PathVariable int custmerId){
		return ResponseEntity.ok(customerService.getCustomerById(custmerId));
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customers")
	ResponseEntity<List<Customer>> getAllCustomers(){
		return ResponseEntity.ok(customerService.getAllCustomer());
		
	}
}

package com.aurionpro.jwtsecured.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.jwtsecured.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
}

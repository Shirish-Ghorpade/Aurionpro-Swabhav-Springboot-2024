package com.aurionpro.connect.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.connect.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

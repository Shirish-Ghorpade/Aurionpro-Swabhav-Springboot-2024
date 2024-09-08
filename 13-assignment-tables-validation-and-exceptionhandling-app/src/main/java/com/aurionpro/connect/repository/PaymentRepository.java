package com.aurionpro.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.connect.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}

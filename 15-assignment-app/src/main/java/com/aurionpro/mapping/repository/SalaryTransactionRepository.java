package com.aurionpro.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mapping.entity.Salary;
import com.aurionpro.mapping.entity.SalaryTransaction;

public interface SalaryTransactionRepository extends JpaRepository<SalaryTransaction, Integer>{


}

package com.aurionpro.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aurionpro.mapping.entity.SalaryAccount;

public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Integer> {
}

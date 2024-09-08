package com.aurionpro.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mapping.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

}

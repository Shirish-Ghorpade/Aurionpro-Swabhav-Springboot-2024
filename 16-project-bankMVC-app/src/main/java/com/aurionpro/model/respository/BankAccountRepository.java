package com.aurionpro.model.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.model.entity.BankAccount;
import com.aurionpro.model.entity.Customer;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{
	Optional<BankAccount> findByAccountNumber(String accountNumber);
	
}

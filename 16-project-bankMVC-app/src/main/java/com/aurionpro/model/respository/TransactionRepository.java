package com.aurionpro.model.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.model.entity.BankAccount;
import com.aurionpro.model.entity.Transaction;
import com.cloudinary.provisioning.Account;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Page<Transaction> findBySenderAccountOrReceiverAccount(BankAccount senderAccount, BankAccount receiverAccount,
			Pageable pageable);
	
	List<Transaction> findBySenderAccountOrReceiverAccount(BankAccount senderAccount, BankAccount receiverAccount);

}

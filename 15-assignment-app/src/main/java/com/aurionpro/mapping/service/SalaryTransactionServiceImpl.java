package com.aurionpro.mapping.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.entity.Client;
import com.aurionpro.mapping.entity.Salary;
import com.aurionpro.mapping.entity.SalaryAccount;
import com.aurionpro.mapping.entity.SalaryTransaction;
import com.aurionpro.mapping.exceptions.NotFoundException;
import com.aurionpro.mapping.repository.SalaryAccountRepository;
import com.aurionpro.mapping.repository.SalaryRepository;
import com.aurionpro.mapping.repository.SalaryTransactionRepository;

@Service
public class SalaryTransactionServiceImpl implements SalaryTransactionService {

	@Autowired
	SalaryTransactionRepository salaryTransactionRepository;

	@Autowired
	SalaryAccountRepository salaryAccountRepository;

	@Autowired
	SalaryRepository salaryRepository;

	@Override
	public SalaryTransaction addTransaction(SalaryTransaction salaryTransaction) {
		salaryTransaction.setTransactionDate(LocalDate.now());
		return salaryTransactionRepository.save(salaryTransaction);
	}

	@Override
	public SalaryTransaction getTransactionById(int id) {
		Optional<SalaryTransaction> optionSalaryTransaction = salaryTransactionRepository.findById(id);
		if (!optionSalaryTransaction.isPresent())
			throw new NotFoundException(id, "Transaction is does not exist of id : ");
		return optionSalaryTransaction.get();
	}

	@Override
	public String deleteTransactionById(int id) {
		Optional<SalaryTransaction> optionSalaryTransaction = salaryTransactionRepository.findById(id);
		if (!optionSalaryTransaction.isPresent())
			throw new NotFoundException(id, "Transaction is does not exist of id : ");

		deleteTransactionById(id);
		return "Transaction Deleted sucessfully";
	}

	@Override
	public SalaryTransaction updateTransactionById(int id, SalaryTransaction salaryTransaction) {
		Optional<SalaryTransaction> optionSalaryTransaction = salaryTransactionRepository.findById(id);
		if (!optionSalaryTransaction.isPresent())
			throw new NotFoundException(id, "Transaction is does not exist of id : ");
		return salaryTransactionRepository.save(salaryTransaction);
	}

	@Override
	public SalaryTransaction allocateSalaryAccount(int salaryTransactionId, SalaryAccount salaryAccount) {
		Optional<SalaryTransaction> optionalSalaryTransaction = salaryTransactionRepository.findById(salaryTransactionId);
		if (!optionalSalaryTransaction.isPresent()) {
			throw new NotFoundException(salaryTransactionId, "Salary transaction is not exist with id : ");
		}
		SalaryTransaction dbSalaryTransaction = optionalSalaryTransaction.get();

		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepository.findById(salaryAccount.getAccountId());
		if (!optionalSalaryAccount.isPresent()) {
			throw new NotFoundException(salaryAccount.getAccountId(), "Salary account is not found with id : ");
		}
		SalaryAccount dbSalaryAccount = optionalSalaryAccount.get();

		dbSalaryTransaction.setSalaryAccount(dbSalaryAccount);
		return salaryTransactionRepository.save(dbSalaryTransaction);
	}

	@Override
	public SalaryTransaction allocateSalary(int salaryTransactionId, Salary salary) {
		Optional<SalaryTransaction> optionalSalaryTransaction = salaryTransactionRepository
				.findById(salaryTransactionId);
		if (!optionalSalaryTransaction.isPresent()) {
			throw new NotFoundException(salaryTransactionId, "Salary Transaction is not found with id : ");
		}
		SalaryTransaction dbSalaryTransaction = optionalSalaryTransaction.get();

		Optional<Salary> optionalSalary = salaryRepository.findById(salary.getSalaryId());
		if (!optionalSalary.isPresent()) {
			throw new NotFoundException(salary.getSalaryId(), "Salary is not found with id : ");
		}
		Salary dbSalary = optionalSalary.get();

		dbSalaryTransaction.setSalary(dbSalary);
		return salaryTransactionRepository.save(dbSalaryTransaction);
	}

}

package com.aurionpro.mapping.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.entity.Bank;
import com.aurionpro.mapping.entity.SalaryAccount;
import com.aurionpro.mapping.entity.SalaryTransaction;
import com.aurionpro.mapping.exceptions.NotFoundException;
import com.aurionpro.mapping.repository.BankRepository;
import com.aurionpro.mapping.repository.SalaryAccountRepository;
import com.aurionpro.mapping.repository.SalaryTransactionRepository;

@Service
public class SalaryAccountServiceImpl implements SalaryAccountService {

	@Autowired
	private SalaryAccountRepository salaryAccountRepository;

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private SalaryTransactionRepository salaryTransactionRepository;

	HashSet<Long> accountNumbers = new HashSet<>();

	@Override
	public SalaryAccount addSalaryAccount(SalaryAccount salaryAccount) {
		salaryAccount.setAccountNumber(generateAccountNumber());
		return salaryAccountRepository.save(salaryAccount);
	}

	@Override
	public SalaryAccount getSalaryAccountByAccountId(int accountId) {
		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepository.findById(accountId);
		if (!optionalSalaryAccount.isPresent()) {
			return null;
		}
		return optionalSalaryAccount.get();
	}

	@Override
	public String deleteSalaryAccountByAccountId(int accountId) {
		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepository.findById(accountId);
		if (!optionalSalaryAccount.isPresent()) {
			throw new NotFoundException(accountId, "Account is not found with id : ");
		}
		salaryAccountRepository.deleteById(accountId);
		return "Account is sucessfully deleted";
	}

	@Override
	public SalaryAccount allocateBank(int accountId, Bank bank) {
		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepository.findById(accountId);
		if (!optionalSalaryAccount.isPresent()) {
			return null;
		}

		SalaryAccount dbSalaryAccount = optionalSalaryAccount.get();
		Optional<Bank> optionalBank = bankRepository.findById(bank.getBankId());
		if (!optionalBank.isPresent()) {
			return null;
		}
		Bank dbBank = optionalBank.get();
		dbSalaryAccount.setBank(dbBank);
		return salaryAccountRepository.save(dbSalaryAccount);
	}

	private Long generateAccountNumber() {
		long accountNumber = 0L;
		while (true) {
			long randomNumber = generateAccountNumberHelper();
			if (!accountNumbers.contains(randomNumber)) {
				accountNumber = randomNumber;
				break;
			}
		}
		return accountNumber;
	}

	private static long generateAccountNumberHelper() {
		Random random = new Random();
		return 10000000000L + (long) (random.nextDouble() * 9000000000L);
	}

	@Override
	public SalaryAccount allocateTransactions(int accountId, List<SalaryTransaction> salaryTransactions) {
		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepository.findById(accountId);
		if (!optionalSalaryAccount.isPresent()) {
			return null;
		}
//		Get salaryAccount
		SalaryAccount salaryAccount = optionalSalaryAccount.get();

//		List of salaryTransactions
		List<SalaryTransaction> dbSalaryTransactions = salaryAccount.getSalaryTransaction();

		salaryTransactions.forEach((salaryTransaction) -> {
			SalaryTransaction emp = salaryTransactionRepository.findById(salaryTransaction.getTransactionId())
					.get();
			emp.setSalaryAccount(salaryAccount);
			dbSalaryTransactions.add(emp);
		});

		salaryAccount.setSalaryTransaction(dbSalaryTransactions);
		return salaryAccountRepository.save(salaryAccount);
	}
}

package com.aurionpro.mapping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.entity.Bank;
import com.aurionpro.mapping.entity.SalaryAccount;
import com.aurionpro.mapping.exceptions.NotFoundException;
import com.aurionpro.mapping.repository.BankRepository;
import com.aurionpro.mapping.repository.SalaryAccountRepository;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	BankRepository bankRepository;

	@Autowired
	SalaryAccountRepository salaryAccountRepository;

	@Override
	public Bank addBank(Bank bank) {
		return bankRepository.save(bank);
	}

	@Override
	public Bank getBankById(int id) {
		Optional<Bank> optionalBank = bankRepository.findById(id);
		if (!optionalBank.isPresent())
			throw new NotFoundException(id, "Bank is not found with id : ");

		return optionalBank.get();
	}

	@Override
	public String deleteBankById(int id) {
		Optional<Bank> optionalBank = bankRepository.findById(id);
		if (!optionalBank.isPresent())
			throw new NotFoundException(id, "Bank is not found with id : ");

		bankRepository.deleteById(id);
		return "Bank is sucessfully deleted";

	}

	@Override
	public Bank updateBankById(int id, Bank bank) {
		Optional<Bank> optionalBank = bankRepository.findById(id);
		if (!optionalBank.isPresent())
			throw new NotFoundException(id, "Bank is not found with id : ");

		return bankRepository.save(bank);
	}

	@Override
	public Bank allocateSalaryAccount(int bankId, List<SalaryAccount> salaryAccounts) {
		Optional<Bank> optionalBank = bankRepository.findById(bankId);
		if (!optionalBank.isPresent()) {
			throw new NotFoundException(bankId, "Bank is not found with id : ");
		}

		Bank dbBank = optionalBank.get();

		List<SalaryAccount> dbsalaryAccounts = dbBank.getSalaryAccounts();

		salaryAccounts.forEach((salaryAccount) -> {
			SalaryAccount temp = salaryAccountRepository.findById(salaryAccount.getAccountId()).get();
			temp.setBank(dbBank);
			dbsalaryAccounts.add(temp);
		});

		dbBank.setSalaryAccounts(dbsalaryAccounts);
		return bankRepository.save(dbBank);
	}

}

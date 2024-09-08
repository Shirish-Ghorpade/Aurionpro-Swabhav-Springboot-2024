package com.aurionpro.mapping.service;

import java.util.List;

import com.aurionpro.mapping.entity.Bank;
import com.aurionpro.mapping.entity.SalaryAccount;
import com.aurionpro.mapping.entity.SalaryTransaction;

public interface SalaryAccountService {

	public SalaryAccount addSalaryAccount(SalaryAccount salaryAccount);

	public SalaryAccount getSalaryAccountByAccountId(int accountId);

	public String deleteSalaryAccountByAccountId(int accountId);
	
	public SalaryAccount allocateBank(int accountId, Bank bank);
	
	public SalaryAccount allocateTransactions(int accountId, List<SalaryTransaction>salaryTransactions);
	
}

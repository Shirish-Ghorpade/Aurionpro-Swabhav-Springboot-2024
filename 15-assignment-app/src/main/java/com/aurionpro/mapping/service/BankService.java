package com.aurionpro.mapping.service;

import java.util.List;

import com.aurionpro.mapping.entity.Bank;
import com.aurionpro.mapping.entity.SalaryAccount;

public interface BankService {

	public Bank addBank(Bank bank);

	public Bank getBankById(int id);

	public String deleteBankById(int id);

	public Bank updateBankById(int id, Bank bank);

	public Bank allocateSalaryAccount(int bankId, List<SalaryAccount> salaryAccount);

}

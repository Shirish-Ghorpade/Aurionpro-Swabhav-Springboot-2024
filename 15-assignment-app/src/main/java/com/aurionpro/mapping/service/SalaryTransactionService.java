package com.aurionpro.mapping.service;

import com.aurionpro.mapping.entity.Salary;
import com.aurionpro.mapping.entity.SalaryAccount;
import com.aurionpro.mapping.entity.SalaryTransaction;

public interface SalaryTransactionService {
	public SalaryTransaction addTransaction(SalaryTransaction salaryTransaction);

	public SalaryTransaction getTransactionById(int id);

	public String deleteTransactionById(int id);

	public SalaryTransaction updateTransactionById(int id, SalaryTransaction salaryTransaction);
	
	public SalaryTransaction allocateSalaryAccount(int SalaryTransactionId, SalaryAccount salaryAccount);
	
	public SalaryTransaction allocateSalary(int SalaryTransactionId, Salary salary);
	
	

}

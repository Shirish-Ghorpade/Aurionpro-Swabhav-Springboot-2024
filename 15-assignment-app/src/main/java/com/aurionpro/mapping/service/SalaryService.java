package com.aurionpro.mapping.service;

import com.aurionpro.mapping.entity.Salary;

public interface SalaryService {
	public Salary addSalary(Salary salary);
	public Salary getSalaryById(int id);
	public String deleteSalaryById(int id);
}

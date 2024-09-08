package com.aurionpro.model.service;

import java.util.List;

import com.aurionpro.model.repository.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployees();
	public Employee addEmployee();
}

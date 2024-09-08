package com.aurionpro.connect.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.aurionpro.connect.entity.Employee;

public interface EmployeeService {
	Page<Employee> getAllEmployees(int pageNo, int pageSize, String sortBy);

	Page<Employee> getAllEmployeesByEmail(String email, int pageNo, int pageSize, String sortBy);

	Optional<Employee> getEmployeeById(int employeeId);

	String addEmployee(Employee employee);

	String deleteEmployee(int employeeId);
}

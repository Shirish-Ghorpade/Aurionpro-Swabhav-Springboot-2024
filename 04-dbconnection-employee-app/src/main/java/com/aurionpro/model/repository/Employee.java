package com.aurionpro.model.repository;

import java.util.List;

public interface Employee {

	public List<Employee> getEmployees();
	public Employee addEmployee(Employee employee);
	
	public List<Employee> searchEmployeeByName(String name);

}

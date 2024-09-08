package com.aurionpro.mapping.service;

import com.aurionpro.mapping.entity.Client;
import com.aurionpro.mapping.entity.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee);

	public Employee getEmployeeById(int id);

	public Employee updateEmployee(int id, Employee employee);

	public String deleteEmployeeById(int id);

	public Employee allocateClient(int employeeId, Client client);

//	SalaryAccount getSalaryByEmployeeId(int EmployeeId); 

}

package com.aurionpro.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aurionpro.model.repository.*;

public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeImpl employeeImpl;
	

	@Override
	public List<Employee> getAllEmployees() {

		return employeeImpl.getEmployees();
	}

	@Override
	public Employee addEmployee() {
		// TODO Auto-generated method stub
		return employeeImpl.addEmployee(employeeImpl);
	}

}

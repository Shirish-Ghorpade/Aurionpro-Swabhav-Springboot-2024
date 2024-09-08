package com.aurionpro.connect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aurionpro.connect.entity.Employee;
import com.aurionpro.connect.respository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Page<Employee> getAllEmployees(int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return employeeRepository.findAll(pageable);
	}

	@Override
	public Page<Employee> getAllEmployeesByEmail(String email, int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return employeeRepository.findAllByEmail(email, pageable);
	}

	@Override
	public Optional<Employee> getEmployeeById(int employeeId) {
		return employeeRepository.findById(employeeId);
	}

	@Override
	public String addEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "We add employee sucessfully";
	}


	@Override
	public String deleteEmployee(int employeeId) {
		employeeRepository.deleteById(employeeId);
		return "Delete employee sucessfully";
	}
}

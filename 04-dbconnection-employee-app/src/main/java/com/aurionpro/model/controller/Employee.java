package com.aurionpro.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurionpro.model.service.EmployeeService;


@RequestMapping("/employeeapp")
public class Employee {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees/{empid}")
	
	public ResponseEntity<List<com.aurionpro.model.repository.Employee>> getAllEmployees(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	public String addEmployee() {
		employeeService.addEmployee();
		return "Added sucessfully";
	}
	
	
	
	
	
	
}

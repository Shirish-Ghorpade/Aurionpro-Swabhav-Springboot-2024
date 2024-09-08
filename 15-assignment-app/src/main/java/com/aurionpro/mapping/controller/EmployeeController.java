package com.aurionpro.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mapping.entity.Client;
import com.aurionpro.mapping.entity.Employee;
import com.aurionpro.mapping.service.EmployeeService;

@RestController
@RequestMapping("/bankapp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employee")
	public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.addEmployee(employee));
	}

	@GetMapping("/employee")
	public ResponseEntity<Employee> getEmployeeById(@RequestParam int id) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}
	
	@PutMapping("/employee")
	public ResponseEntity<Employee> updateEmployeeById(@RequestParam int id, @RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
	}
	
	@DeleteMapping("/employee")
	public ResponseEntity<String>deleteEmployeeById(@RequestParam int id){
		return ResponseEntity.ok(employeeService.deleteEmployeeById(id));
	}
	
	@PutMapping("/employee/client")
	public ResponseEntity<Employee> allocateClient(@RequestParam int id, @RequestBody Client client) {
		return ResponseEntity.ok(employeeService.allocateClient(id, client));
	}



}

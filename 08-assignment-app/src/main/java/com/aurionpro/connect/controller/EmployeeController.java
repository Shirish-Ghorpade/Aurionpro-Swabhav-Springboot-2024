package com.aurionpro.connect.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.connect.entity.Employee;
import com.aurionpro.connect.service.EmployeeService;

@RestController
@RequestMapping("/employeeapp")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public ResponseEntity<Page<Employee>> getAllEmployeeByEmail(@RequestParam(required = false) String email,
			@RequestParam(defaultValue = "0", required = false) int pageNo,
			@RequestParam(defaultValue = "3", required = false) int pageSize,
			@RequestParam(defaultValue = "employeeId", required = false) String sortBy) {
		if (email != null)
			return ResponseEntity.ok(employeeService.getAllEmployeesByEmail(email, pageNo, pageSize, sortBy));
		return ResponseEntity.ok(employeeService.getAllEmployees(pageNo, pageSize, sortBy));
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable int id) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}

	@PostMapping("/employee")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.addEmployee(employee));
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
		return ResponseEntity.ok(employeeService.deleteEmployee(id));
	}

}

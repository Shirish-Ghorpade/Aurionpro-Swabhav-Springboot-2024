package com.aurionpro.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mapping.entity.Salary;
import com.aurionpro.mapping.service.SalaryService;

@RestController
@RequestMapping("/bankapp")
public class SalaryController {

	@Autowired
	SalaryService salaryService;

	@PostMapping("/salary")
	public ResponseEntity<Salary> addNewSalary(@RequestBody Salary salary) {
		return ResponseEntity.ok(salaryService.addSalary(salary));
	}

	@GetMapping("/salary")
	public ResponseEntity<Salary> getSalaryById(@RequestParam int id) {
		return ResponseEntity.ok(salaryService.getSalaryById(id));
	}
	
	@DeleteMapping("/salary")
	public ResponseEntity<String> deleteSalaryById(@RequestParam int id) {
		return ResponseEntity.ok(salaryService.deleteSalaryById(id));
		
	}
}

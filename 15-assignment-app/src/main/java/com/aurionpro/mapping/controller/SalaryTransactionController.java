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

import com.aurionpro.mapping.entity.Salary;
import com.aurionpro.mapping.entity.SalaryAccount;
import com.aurionpro.mapping.entity.SalaryTransaction;
import com.aurionpro.mapping.service.SalaryTransactionService;

@RestController
@RequestMapping("/bankapp")
public class SalaryTransactionController {

	@Autowired
	SalaryTransactionService salaryTransactionService;

	@PostMapping("/salarytransaction")
	public ResponseEntity<SalaryTransaction> addNewSalaryTransaction(@RequestBody SalaryTransaction salaryTransaction) {
		return ResponseEntity.ok(salaryTransactionService.addTransaction(salaryTransaction));
	}

	@GetMapping("/salarytransaction")
	public ResponseEntity<SalaryTransaction> getSalaryTransactionById(@RequestParam int id) {
		return ResponseEntity.ok(salaryTransactionService.getTransactionById(id));
	}

	@DeleteMapping("/salarytransaction")
	public ResponseEntity<String> deleteSalaryTransactionById(@RequestParam int id) {
		return ResponseEntity.ok(salaryTransactionService.deleteTransactionById(id));
	}

	@PutMapping("/salarytransaction")
	public ResponseEntity<SalaryTransaction> updateSalaryTransactionById(@RequestParam int id, @RequestBody SalaryTransaction salaryTransaction) {
		return ResponseEntity.ok(salaryTransactionService.updateTransactionById(id, salaryTransaction));
	}
	
	@PutMapping("/salarytransaction/salaryaccount")
	public ResponseEntity<SalaryTransaction> allocateSalaryAccount(@RequestParam int id, @RequestBody SalaryAccount salaryAccount) {
		return ResponseEntity.ok(salaryTransactionService.allocateSalaryAccount(id, salaryAccount));
	}
	
	@PutMapping("/salarytransaction/salary")
	public ResponseEntity<SalaryTransaction> allocateSalary(@RequestParam int id, @RequestBody Salary salary) {
		return ResponseEntity.ok(salaryTransactionService.allocateSalary(id, salary));
	}
	
	
}

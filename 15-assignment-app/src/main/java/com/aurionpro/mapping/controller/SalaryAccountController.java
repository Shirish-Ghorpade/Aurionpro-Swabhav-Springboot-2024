package com.aurionpro.mapping.controller;

import java.util.List;

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

import com.aurionpro.mapping.entity.Bank;
import com.aurionpro.mapping.entity.SalaryAccount;
import com.aurionpro.mapping.entity.SalaryTransaction;
import com.aurionpro.mapping.service.SalaryAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class SalaryAccountController {

	@Autowired
	SalaryAccountService salaryAccountService;

	@PostMapping("/salaryaccount")
	ResponseEntity<SalaryAccount> addSalaryAccount(@Valid @RequestBody SalaryAccount salaryAccount) {
		return ResponseEntity.ok(salaryAccountService.addSalaryAccount(salaryAccount));
	}

	@GetMapping("/salaryaccount")
	ResponseEntity<SalaryAccount> getSalaryAccountById(@Valid @RequestParam int accountId) {
		return ResponseEntity.ok(salaryAccountService.getSalaryAccountByAccountId(accountId));
	}

	@DeleteMapping("/salaryaccount")
	ResponseEntity<String> deleteSalaryAccountById(@Valid @RequestParam int accountId) {
		return ResponseEntity.ok(salaryAccountService.deleteSalaryAccountByAccountId(accountId));
	}

	@PutMapping("/salaryaccount/bank")
	ResponseEntity<SalaryAccount> allocateBank(@Valid @RequestParam int accountId, @Valid @RequestBody Bank bank) {
		return ResponseEntity.ok(salaryAccountService.getSalaryAccountByAccountId(accountId));
	}

	@PutMapping("/salaryaccount/salaryTransactions")
	ResponseEntity<SalaryAccount> allocateTransactions(@Valid @RequestParam int accountId,
			@Valid @RequestBody List<SalaryTransaction> salaryTransactions) {
		return ResponseEntity
				.ok(salaryAccountService.allocateTransactions(accountId, salaryTransactions));
	}
}

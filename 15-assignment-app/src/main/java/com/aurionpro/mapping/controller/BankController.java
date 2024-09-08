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
import com.aurionpro.mapping.service.BankService;

@RestController
@RequestMapping("/bankapp")
public class BankController {

	@Autowired
	BankService bankService;

	@PostMapping("/bank")
	public ResponseEntity<Bank> addNewBank(@RequestBody Bank bank) {
		return ResponseEntity.ok(bankService.addBank(bank));
	}

	@GetMapping("/bank")
	public ResponseEntity<Bank> getBankById(@RequestParam int id) {
		return ResponseEntity.ok(bankService.getBankById(id));
	}

	@DeleteMapping("/bank")
	public ResponseEntity<String> deleteBankById(@RequestParam int id) {
		return ResponseEntity.ok(bankService.deleteBankById(id));
	}

	@PutMapping("/bank")
	public ResponseEntity<Bank> updateBankById(@RequestParam int id, @RequestBody Bank bank) {
		return ResponseEntity.ok(bankService.updateBankById(id, bank));
	}

	@PutMapping("/bank/salaryaccount")
	public ResponseEntity<Bank> allocateSalaryAccount(@RequestParam int bankId,
			@RequestBody List<SalaryAccount> salaryAccounts) {
		return ResponseEntity.ok(bankService.allocateSalaryAccount(bankId, salaryAccounts));
	}

}

package com.aurionpro.connect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.connect.entity.Loan;
import com.aurionpro.connect.service.LoanService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/loanapp")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@GetMapping("/loan")
	public List<Loan> getAllLoan() {
		return loanService.getAllLoans();
	}

	@GetMapping("/loan/{id}")
	public Loan getLoanById(@PathVariable int id) {
		return loanService.getLoanById(id);
	}

	@Transactional
	@PostMapping("/loan")
	public void addLoan(@RequestBody Loan loan) {
		loanService.addLoan(loan);
	}

	@Transactional
	@PutMapping("/{id}")
	public void updateLoan(@PathVariable int id, @RequestBody Loan loan) {
		loanService.updateLoan(loan);
	}

	@Transactional
	@DeleteMapping("/loan/{id}")
	public void deleteLoan(@PathVariable int id) {
		loanService.deleteLoan(id);
	}
}

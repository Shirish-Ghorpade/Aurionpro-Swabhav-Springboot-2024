package com.aurionpro.mapping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.entity.Salary;
import com.aurionpro.mapping.exceptions.NotFoundException;
import com.aurionpro.mapping.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	SalaryRepository salaryRepository;

	@Override
	public Salary addSalary(Salary salary) {
		// TODO Auto-generated method stub
		return salaryRepository.save(salary);
	}

	@Override
	public Salary getSalaryById(int id) {
		// TODO Auto-generated method stub

		Optional<Salary> optionalSalary = salaryRepository.findById(id);
		if (!optionalSalary.isPresent())
			throw new NotFoundException(id, "Salary is not exist with id : ");
		return optionalSalary.get();
	}

	@Override
	public String deleteSalaryById(int id) {
		// TODO Auto-generated method stub

		Optional<Salary> optionalSalary = salaryRepository.findById(id);
		if (!optionalSalary.isPresent())
			throw new NotFoundException(id, "Salary is not exist with id : ");
		salaryRepository.deleteById(id);
		return "Deleted Sucessfully";
	}
}

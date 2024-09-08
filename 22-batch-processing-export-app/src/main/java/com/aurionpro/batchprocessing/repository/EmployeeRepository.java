package com.aurionpro.batchprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.batchprocessing.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

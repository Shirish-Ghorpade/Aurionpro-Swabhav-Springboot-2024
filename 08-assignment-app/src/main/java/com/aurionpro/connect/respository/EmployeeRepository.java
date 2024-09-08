package com.aurionpro.connect.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.connect.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	Page<Employee> findAllByEmail(String email, Pageable pageable);
}

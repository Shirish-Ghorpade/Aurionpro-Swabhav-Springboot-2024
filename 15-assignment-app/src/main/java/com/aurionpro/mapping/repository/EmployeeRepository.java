package com.aurionpro.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mapping.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}

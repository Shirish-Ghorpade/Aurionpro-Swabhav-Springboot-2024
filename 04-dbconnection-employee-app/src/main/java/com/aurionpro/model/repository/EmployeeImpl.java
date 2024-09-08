package com.aurionpro.model.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EmployeeImpl implements Employee {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e", Employee.class);
		return query.getResultList();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchEmployeeByName(String name) {
		// TODO Auto-generated method stub
		TypedQuery<Employee> query = entityManager.createQuery("select e employee from employee e where name=:theName", Employee.class);
		
		return query.getResultList();
		
		
		
		
		
	}

//	@Override
//	@Transactional
//	public Employee addEmployee(Employee employee) {
//		// TODO Auto-generated method stub
//		entityManager.persist(employee);
//	}

}

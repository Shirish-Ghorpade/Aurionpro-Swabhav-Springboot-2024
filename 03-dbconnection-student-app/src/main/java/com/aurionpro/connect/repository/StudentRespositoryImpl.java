package com.aurionpro.connect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aurionpro.connect.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Component
public class StudentRespositoryImpl implements StudentRespository {

	@Autowired
	private EntityManager manager;

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		TypedQuery<Student> query = manager.createQuery("select s from Student s", Student.class);
		return query.getResultList();
	}

}

package com.aurionpro.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.connect.entity.Student;
import com.aurionpro.connect.repository.StudentRespository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRespository studentRespository;

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRespository.getAllStudents();
	}

}

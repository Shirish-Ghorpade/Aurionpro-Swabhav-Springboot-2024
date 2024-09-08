package com.aurionpro.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.model.entity.Student;
import com.aurionpro.model.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public List<Student> getAllStudentsByName(String name){
		return studentRepository.findAllByName(name);
	}
	
	
	public Optional<Student> getStudentById(int studentId){
		return studentRepository.findById(studentId);
	}
	
	
	public String addStudent(Student student) {
		studentRepository.save(student);
		return "Added student data sucessfully";
	}
	
	public String deleteStudent(int studnetId) {
		studentRepository.deleteById(studnetId);
		return "delete student data sucessfully";
	}
	
	
	
}

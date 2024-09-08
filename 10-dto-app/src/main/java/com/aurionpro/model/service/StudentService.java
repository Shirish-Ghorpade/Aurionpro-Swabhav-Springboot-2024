package com.aurionpro.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.model.dto.PageResponse;
import com.aurionpro.model.entity.Student;
import com.aurionpro.model.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public PageResponse<Student> getAllStudents(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Student> studentPage = studentRepository.findAll(pageable);
		PageResponse<Student> studentPageResponse = new PageResponse<Student>(); 
		studentPageResponse.setTotalPages(pageSize);
		studentPageResponse.setSize(studentPage.getSize());
		studentPageResponse.setTotalElements(studentPage.getTotalElements());
		studentPageResponse.setContent(studentPage.getContent());
		studentPageResponse.setLastPage(studentPage.isLast());
		return studentPageResponse;
	}

	public Page<Student> getAllStudentsByName(String name, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return studentRepository.findAllByName(name, pageable);
	}

	public Optional<Student> getStudentById(int studentId) {
		return studentRepository.findById(studentId);
	}

	public String addStudent(Student student) {
		studentRepository.save(student);
		return "Add student sucessfully";
	}

	public String deleteStudent(int studnetId) {
		studentRepository.deleteById(studnetId);
		return "Delete student sucessfully";
	}

}

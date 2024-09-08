package com.aurionpro.connect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.connect.entity.Student;
import com.aurionpro.connect.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllEmployees() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}
}

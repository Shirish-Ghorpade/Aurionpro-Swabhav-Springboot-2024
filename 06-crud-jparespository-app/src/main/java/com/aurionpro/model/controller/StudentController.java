package com.aurionpro.model.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.model.entity.Student;
import com.aurionpro.model.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudent() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@GetMapping("/students/{name}")
	public ResponseEntity<List<Student>> getAllStudentByName(@RequestParam(required = false) String name) {
		if(name != null)
			return ResponseEntity.ok(studentService.getAllStudentsByName(name));
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<Optional<Student>> getStudentById(@PathVariable int id) {
		return ResponseEntity.ok(studentService.getStudentById(id));
	}

	@PostMapping("/student")
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		return ResponseEntity.ok(studentService.addStudent(student));
	}

	@DeleteMapping("/student/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
		return ResponseEntity.ok(studentService.deleteStudent(id));
	}

}

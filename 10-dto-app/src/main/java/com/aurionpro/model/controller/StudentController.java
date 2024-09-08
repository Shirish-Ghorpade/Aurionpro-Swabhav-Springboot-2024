package com.aurionpro.model.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.model.dto.PageResponse;
import com.aurionpro.model.entity.Student;
import com.aurionpro.model.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("/student")
	public ResponseEntity<PageResponse<Student>> getAllStudents(@RequestParam(defaultValue = "1", required = false) int pageNo,
			@RequestParam(defaultValue = "5", required = false) int pageSize) {
		return ResponseEntity.ok(studentService.getAllStudents(pageNo, pageSize));
	}

	
	@GetMapping("/students/name")
	public ResponseEntity<PageResponse<Student>> getAllStudentByName(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "1", required = false) int pageNo,
			@RequestParam(defaultValue = "4", required = false) int pageSize) {
//		if (name != null)
//			return ResponseEntity.ok(studentService.getAllStudentsByName(name, pageNo, pageSize));
		return ResponseEntity.ok(studentService.getAllStudents(pageNo, pageSize));
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

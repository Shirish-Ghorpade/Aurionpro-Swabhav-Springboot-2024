package com.aurionpro.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mapping.dto.PageResponse;
import com.aurionpro.mapping.dto.StudentDto;
import com.aurionpro.mapping.entity.Address;
import com.aurionpro.mapping.entity.Student;
import com.aurionpro.mapping.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/student")
	public ResponseEntity<StudentDto> addNewStudent(@RequestBody Student student) {
		return ResponseEntity.ok(studentService.addStudent(student));
	}

	@GetMapping("/student")
	public ResponseEntity<PageResponse<StudentDto>> getAllStudents(
			@RequestParam(defaultValue = "1", required = false) int pageNo,
			@RequestParam(defaultValue = "5", required = false) int pageSize) {
		return ResponseEntity.ok(studentService.getAllStudents(pageNo, pageSize));
	}

	@GetMapping("/student/{rollNumber}")
	public ResponseEntity<Address> getAddressByStudentRollNumber(@PathVariable int rollNumber) {
		return ResponseEntity.ok(studentService.getAddressByStudentRollNumber(rollNumber));
	}

	@PutMapping("/student")
	public ResponseEntity<Address> updateAddressByStudentRollNumber(@RequestParam int rollNumber,
			@RequestBody Address address) {
		return ResponseEntity.ok(studentService.updateAddressByStudentRollNumber(rollNumber, address));
	}

	@PutMapping("/assigncourses")
	public ResponseEntity<StudentDto> assignCourses(@RequestParam int rollNumber, @RequestBody List<Integer>courseIds) {
		return ResponseEntity.ok(studentService.assignCourses(rollNumber, courseIds));
	}
}

package com.aurionpro.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mapping.dto.CourseDto;
import com.aurionpro.mapping.dto.InstructorDto;
import com.aurionpro.mapping.dto.StudentDto;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Instructor;
import com.aurionpro.mapping.service.InstructorService;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {
	@Autowired
	private InstructorService instructorService;

	@PostMapping("/instructors")
	public ResponseEntity<InstructorDto> addNewInstructor(@RequestBody InstructorDto instructorDto) {
		return ResponseEntity.ok(instructorService.addInstructor(instructorDto));
	}

	@PutMapping("instructors/courses")
	public ResponseEntity<Instructor> allocateCourses(@RequestParam int instructorId,
			@RequestBody List<Course> courses) {
		return ResponseEntity.ok(instructorService.allocateCourses(instructorId, courses));
	}

	@GetMapping("/instructors")
	public ResponseEntity<InstructorDto> getInstructorsById(@RequestParam int instructorId) {
		return ResponseEntity.ok(instructorService.getInstructorById(instructorId));
	}

	@GetMapping("/instructor/getcourses")
	public ResponseEntity<List<CourseDto>> getCoursesByInstructorId(@RequestParam int instructorId) {
		return ResponseEntity.ok(instructorService.getCoursesByInstructorId(instructorId));
	}
	
	@GetMapping("/instructor/getstudents")
	public ResponseEntity<List<StudentDto>> getAllStudentsByInstructorId(@RequestParam int instructorId) {
		return ResponseEntity.ok(instructorService.getAllStudentsByInstructorId(instructorId));
	}
	
	

}

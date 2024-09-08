package com.aurionpro.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mapping.dto.CourseDto;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Instructor;
import com.aurionpro.mapping.service.CourseService;

@RestController
@RequestMapping("/studentapp")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@PostMapping("/courses")
	public ResponseEntity<Course> addNewCourse(@RequestBody CourseDto CourseDto) {
		return ResponseEntity.ok(courseService.addCourse(CourseDto));
	}
	
	@PutMapping("courses")
	public ResponseEntity<Course> allocateCourses(@RequestParam int courseId,
			@RequestBody Instructor instructor) {
		return ResponseEntity.ok(courseService.allocateInstructor(courseId, instructor));
	}
	
	@PutMapping("/assignstudents")
	public ResponseEntity<CourseDto> assignStudents(@RequestParam int courseId,
			@RequestBody List<Integer>studentIds) {
		return ResponseEntity.ok(courseService.assignStudents(courseId, studentIds));
	}
	
	
}
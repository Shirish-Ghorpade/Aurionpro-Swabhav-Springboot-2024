package com.aurionpro.mapping.service;


import java.util.List;

import com.aurionpro.mapping.dto.CourseDto;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Instructor;

public interface CourseService {
	Course addCourse(CourseDto courseDto);
	
	Course allocateInstructor(int courseId, Instructor instructor);
	
	CourseDto assignStudents(int courseId, List<Integer> studentIds);
}

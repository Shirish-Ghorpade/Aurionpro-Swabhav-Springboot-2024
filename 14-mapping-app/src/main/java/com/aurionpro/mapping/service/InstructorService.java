package com.aurionpro.mapping.service;

import java.util.List;

import com.aurionpro.mapping.dto.CourseDto;
import com.aurionpro.mapping.dto.InstructorDto;
import com.aurionpro.mapping.dto.PageResponse;
import com.aurionpro.mapping.dto.StudentDto;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Instructor;

public interface InstructorService {

	public InstructorDto addInstructor(InstructorDto instructorDto);

	public InstructorDto getInstructorById(int instrcutorId);
	
	public PageResponse<InstructorDto> getAllInstructor(int pageNo, int pageSize);
	
	public List<CourseDto> getInstructorCourses(int instructorId);
	
	public Instructor allocateCourses(int instructionId, List<Course> courses);
	
	public InstructorDto allocateCourses2(int instructionId, List<Integer> courses);
	
	public List<CourseDto> getCoursesByInstructorId(int instructorId);
	
	public List<StudentDto> getAllStudentsByInstructorId(int instructorId);
	

	
	

}

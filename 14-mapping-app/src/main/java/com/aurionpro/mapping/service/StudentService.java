package com.aurionpro.mapping.service;

import java.util.List;

import com.aurionpro.mapping.dto.PageResponse;
import com.aurionpro.mapping.dto.StudentDto;
import com.aurionpro.mapping.entity.Address;
import com.aurionpro.mapping.entity.Student;

public interface StudentService {
	StudentDto addStudent(Student student);

	Address getAddressByStudentRollNumber(int rollNumber);
	
	Address updateAddressByStudentRollNumber(int rollNumber, Address address);
	
	PageResponse<StudentDto> getAllStudents(int pageNo, int pageSize);
	
	StudentDto getStudentByRollNumber(int rollNumber);
	
	StudentDto assignCourses(int rollNumber, List<Integer>courseIds);
	
}

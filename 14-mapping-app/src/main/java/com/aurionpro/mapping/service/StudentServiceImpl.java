package com.aurionpro.mapping.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.dto.PageResponse;
import com.aurionpro.mapping.dto.StudentDto;
import com.aurionpro.mapping.entity.Address;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Student;
import com.aurionpro.mapping.repository.CourseRepository;
import com.aurionpro.mapping.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CourseRepository courseRepository;

	@Override
	public StudentDto addStudent(Student student) {
		studentRepository.save(student);
		return toStudentDtoMapper(student);
		
	}

	@Override
	public Address getAddressByStudentRollNumber(int rollNumber) {
		Optional<Student> studentOption = studentRepository.findById(rollNumber);
		if (!studentOption.isPresent())
			return null;
		Student dbStudent = studentOption.get();
		return dbStudent.getAddress();
	}

	@Override
	public Address updateAddressByStudentRollNumber(int rollNumber, Address address) {
		Optional<Student> studentOption = studentRepository.findById(rollNumber);
		if (!studentOption.isPresent())
			return null;
		Student dbStudent = studentOption.get();
		Address dbAddress = dbStudent.getAddress();

		dbAddress.setCityName(address.getCityName());
		dbStudent.setAddress(dbAddress);
		studentRepository.save(dbStudent);
		return dbStudent.getAddress();
	}
	
	
	
	public StudentDto toStudentDtoMapper(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setAge(student.getAge());
		studentDto.setName(student.getName());
		studentDto.setRollNumber(student.getRollNumber());
		return studentDto;
	}
	
	private Student toStudentMapper(StudentDto studentDto) {
		Student student = new Student();
		student.setAge(studentDto.getAge());
		student.setName(studentDto.getName());
		return student;
	}

	@Override
	public PageResponse<StudentDto> getAllStudents(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Student> studentPage = studentRepository.findAll(pageable);
		
		PageResponse<StudentDto> response = new PageResponse<>();
		
		response.setTotalPages(studentPage.getTotalPages());
		response.setTotalElements(studentPage.getTotalElements());
		response.setSize(studentPage.getSize());
		response.setLastPage(studentPage.isLast());
		
		
//		This logic is for setting the content in DTO
		List<StudentDto> studentDtoList = new ArrayList<>();
		 studentPage.getContent().forEach(student -> {
			 studentDtoList.add(toStudentDtoMapper(student));
		 });
		 
//		 Here we acheive goal
		 response.setContent(studentDtoList);
		 return response;
	}

	@Override
	public StudentDto getStudentByRollNumber(int rollNumber) {
		Student student = studentRepository.findById(rollNumber).orElseThrow(()-> new NullPointerException("Student not found"));
		
		return toStudentDtoMapper(student);
	}

	@Override
	public StudentDto assignCourses(int rollNumber, List<Integer> courseIds) {
		Student dbStudent = studentRepository.findById(rollNumber).orElseThrow(()->new NullPointerException());
		
		Set<Course> existingCourses = dbStudent.getCourses();
		if(existingCourses==null) {
			existingCourses=new HashSet<>();
		}
		
		Set<Course> coursesToAdd = new HashSet<>();
		
		courseIds.forEach((id)->{
			Course course = courseRepository.findById(id).orElseThrow(()-> new NullPointerException());
			
			List<Student> existingStudents = course.getStudents();
			if(existingStudents == null) {
				existingStudents = new ArrayList<>();
			}
			existingStudents.add(dbStudent);
			coursesToAdd.add(course);
		});
		
		existingCourses.addAll(coursesToAdd);
		dbStudent.setCourses(existingCourses);
		
		return toStudentDtoMapper(studentRepository.save(dbStudent));
		
	}


}

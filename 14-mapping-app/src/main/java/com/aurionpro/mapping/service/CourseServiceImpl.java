package com.aurionpro.mapping.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.dto.CourseDto;
import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Instructor;
import com.aurionpro.mapping.entity.Student;
import com.aurionpro.mapping.repository.CourseRepository;
import com.aurionpro.mapping.repository.InstructorRepository;
import com.aurionpro.mapping.repository.StudentRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	InstructorRepository instructorRepository;
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	public Course addCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setCourseName(courseDto.getCourseName());
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
		return courseRepository.save(course);
	}

	@Override
	public Course allocateInstructor(int courseId, Instructor instructor) {
		Optional<Course> optionalCourse = courseRepository.findById(courseId);
		if (!optionalCourse.isPresent()) {
			return null;
		}

		Course dbCourse = optionalCourse.get();
		Optional<Instructor> optionalInstructor = instructorRepository.findById(instructor.getInstructorId());
		if (!optionalInstructor.isPresent()) {
			return null;
		}
		Instructor dbInstructor = optionalInstructor.get();
		dbCourse.setInstructor(dbInstructor);
		return courseRepository.save(dbCourse);
	}

	private Instructor getInstructor(int instructorId) {
		Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
		if (!optionalInstructor.isPresent()) {
			return null;
		}
		return optionalInstructor.get();
	}

	public static List<Course> coursesOfInstructor(int instructorId) {
//		Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
//		if (!optionalInstructor.isPresent()) {
//			return null;
//		}
//		return optionalInstructor.get().getCourses();
		return null;
	}

	public static Course getCourseById(int courseId) {
//		Optional<Course> courseOptional = courseRepository.findById(courseId);
//		return courseOptional.get();
		return null;
	}
	
	@Override
	public CourseDto assignStudents(int courseId, List<Integer> studentIds) {
		Course dbCourse = courseRepository.findById(courseId).orElseThrow(()->new NullPointerException());
		
		List<Student> existingStudents = dbCourse.getStudents();
		if(existingStudents==null) {
			existingStudents=new ArrayList<>();
		}
		
		List<Student> studentsToAdd = new ArrayList<>();
		
		studentIds.forEach((id)->{
			Student dbStudent = studentRepository.findById(id).orElseThrow(()-> new NullPointerException());
			
			Set<Course>existingCourses  = dbStudent.getCourses();
			
			if(existingCourses == null) {
				existingCourses = new HashSet<>();
			}
			existingCourses.add(dbCourse);
			dbStudent.setCourses(existingCourses);
			studentsToAdd.add(dbStudent);
		});
		
		existingStudents.addAll(studentsToAdd);
		dbCourse.setStudents(existingStudents);
		return toCourseDtoMapper(courseRepository.save(dbCourse));
	}
	
	public CourseDto toCourseDtoMapper(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseName(course.getCourseName());
		courseDto.setDuration(course.getDuration());
		courseDto.setFees(course.getFees());
		return courseDto;
	}

}

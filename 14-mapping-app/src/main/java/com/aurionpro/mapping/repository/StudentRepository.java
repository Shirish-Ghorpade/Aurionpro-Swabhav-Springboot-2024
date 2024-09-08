package com.aurionpro.mapping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aurionpro.mapping.entity.Course;
import com.aurionpro.mapping.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	@Query("SELECT s FROM Student s JOIN s.courses c WHERE c IN :courses")
	List<Student> findByCoursesIn(@Param("courses") List<Course> courses);
}

package com.aurionpro.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mapping.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}

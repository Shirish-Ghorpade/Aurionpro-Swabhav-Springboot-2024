package com.aurionpro.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mapping.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

}

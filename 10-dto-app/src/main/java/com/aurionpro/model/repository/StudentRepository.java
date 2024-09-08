package com.aurionpro.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.model.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	Page<Student> findAllByName(String name, Pageable pageable);
}

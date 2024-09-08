package com.aurionpro.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "students")
public class Student {

	@Column(name = "rollNumber")
	@Id
	private int rollNumber;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

}

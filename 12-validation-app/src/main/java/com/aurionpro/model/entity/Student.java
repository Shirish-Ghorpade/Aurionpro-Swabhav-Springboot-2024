package com.aurionpro.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@NotNull
	private int rollNumber;

	@Column(name = "name")
	@NotEmpty
	@Size(min=2)
	private String name;

	@NotNull
	@Column(name = "age")
	private int age;

}

package com.aurionpro.mapping.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InstructorDto {
	@Column(name = "instructorId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instructorId;

	@Column(name = "InstructorName")
	private String instructorName;

	@Column(name = "email")
	private String email;

	@Column(name = "qualifiaction")
	private String qualification;
}

package com.aurionpro.connect.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "PassengerInfo")
public class PassengerInfo {
	@Column(name = "passengerId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long passengerId;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "source")
	private String source;
	@Column(name = "destination")
	private String destination;
	@Column(name = "travelDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date travelDate;
	@Column(name = "pickupTime")
	private String pickupTime;
	@Column(name="arrivalTime")
	private String arrivalTime;
	@Column(name = "fare")
	private double fare;

}

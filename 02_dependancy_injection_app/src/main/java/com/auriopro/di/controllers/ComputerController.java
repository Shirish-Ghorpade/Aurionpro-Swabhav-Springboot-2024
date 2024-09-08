package com.auriopro.di.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auriopro.di.entity.Computer;
import com.auriopro.di.entity.Harddisk;

@RestController
public class ComputerController {

	@Autowired
	private Computer computer;

	@Autowired
	private Harddisk harddisk;

	@GetMapping("/computer")
	private Computer getComputer() {
		return computer;
	}
	
	@GetMapping("/harddisk")
	private Harddisk getHarddisk() {
		return harddisk;
	}
}

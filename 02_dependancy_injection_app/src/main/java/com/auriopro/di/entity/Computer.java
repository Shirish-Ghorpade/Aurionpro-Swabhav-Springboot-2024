package com.auriopro.di.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class Computer {
	@Autowired
	private Harddisk harddisk;
	private String name;

	public Computer() {
	}

	public Computer(Harddisk harddisk, String name) {
		super();
		this.harddisk = harddisk;
		this.name = name;
	}

	public Harddisk getHarddisk() {
		return harddisk;
	}

	public void setHarddisk(Harddisk harddisk) {
		this.harddisk = harddisk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Computer [harddisk=" + harddisk + ", name=" + name + "]";
	}

}

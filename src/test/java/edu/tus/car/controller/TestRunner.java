package edu.tus.car.controller;

import com.intuit.karate.junit5.Karate;
import com.intuit.karate.junit5.Karate.Test;

public class TestRunner {
	
	@Test
	public Karate getAllCars() {
		return Karate.run("getAllCars").relativeTo(getClass());
	}
	
	@Test
	public Karate addCar() {
		return Karate.run("addCar").relativeTo(getClass());
	}
	
	@Test
	public Karate getCarByID() {
		return Karate.run("getCarByID").relativeTo(getClass());
	}
	
	@Test
	public Karate deleteCar() {
		return Karate.run("deleteCar").relativeTo(getClass());
	}
}

package edu.tus.car.controller;

import com.intuit.karate.junit5.Karate;
import com.intuit.karate.junit5.Karate.Test;

public class TestGetRunner {
	
	@Test
	public Karate getAllCars() {
		return Karate.run("get").relativeTo(getClass());
	}
	
}

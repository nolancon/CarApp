package edu.tus.car.controller;

import com.intuit.karate.junit5.Karate;
import com.intuit.karate.junit5.Karate.Test;

public class TestAddAndDeleteRunner {
	@Karate.Test
	public Karate addCar() {
		return Karate.run("addAndDeleteCar").relativeTo(getClass());
	}
}

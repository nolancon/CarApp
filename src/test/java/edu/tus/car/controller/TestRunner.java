package edu.tus.car.controller;

import com.intuit.karate.junit5.Karate;
import com.intuit.karate.junit5.Karate.Test;

public class TestRunner {
	
	@Test
	public Karate getAll() {
		return Karate.run("getAll").relativeTo(getClass());
	}
	
	@Test
	public Karate post() {
		return Karate.run("post").relativeTo(getClass());
	}
	
	@Test
	public Karate getByID() {
		return Karate.run("getByID").relativeTo(getClass());
	}
	
	@Test
	public Karate deleteByID() {
		return Karate.run("deleteByID").relativeTo(getClass());
	}
}

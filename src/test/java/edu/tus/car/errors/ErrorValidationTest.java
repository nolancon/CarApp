package edu.tus.car.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.tus.car.model.Car;


class ErrorValidationTest {
	private ErrorValidation errorValidation;
	
	@BeforeEach
	public void setUp(){
		errorValidation = new ErrorValidation();
	}
	
	@Test
	public void yearNotOKTest() {
		Car car = new Car();

		car.setYear(2019);
		assertEquals(errorValidation.yearNotOK(car), true);
		
		car.setYear(2020);
		assertEquals(errorValidation.yearNotOK(car), false);
		
		car.setYear(2021);
		assertEquals(errorValidation.yearNotOK(car), false);
		
		car.setYear(0);
		assertEquals(errorValidation.yearNotOK(car), true);
	}

	@Test
	public void colorNotOKTest() {		
		Car car = new Car();
		
		car.setColor("RED");
		assertEquals(errorValidation.colorNotOK(car), false);
		
		car.setColor("GREEN");
		assertEquals(errorValidation.colorNotOK(car), false);
		
		car.setColor("BLACK");
		assertEquals(errorValidation.colorNotOK(car), false);
		
		car.setColor("SILVER");
		assertEquals(errorValidation.colorNotOK(car), false);

		car.setColor("BLUE");
		assertEquals(errorValidation.colorNotOK(car), true);
		
		car.setColor("");
		assertEquals(errorValidation.colorNotOK(car), true);
	}
	
	@Test
	public void checkMakeAndModelNotAllowedTest() {		
		Car car = new Car();
		
		car.setMake("MERCEDES");
		car.setModel("E220");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("mErCeDes");
		car.setModel("e220");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("MERCEDES");
		car.setModel("A6");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), true);
		
		car.setMake("AUDI");
		car.setModel("A6");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("aUdI");
		car.setModel("a6");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("AUDI");
		car.setModel("E220");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), true);
		
		car.setMake("VOLKSVAGEN");
		car.setModel("ARTEON");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("vOlKsVaGeN");
		car.setModel("aRtEoN");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("VOLKSVAGEN");
		car.setModel("320");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), true);
		
		car.setMake("BMW");
		car.setModel("320");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("bMw");
		car.setModel("320");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("BMW");
		car.setModel("32");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), true);
		
		car.setMake("FERRARI");
		car.setModel("F40");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("fErRaRi");
		car.setModel("f40");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("FERRARI");
		car.setModel("GT4");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), true);
		
		car.setMake("PORSCHE");
		car.setModel("GT4");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("pOrScHe");
		car.setModel("gT4");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), false);
		
		car.setMake("PORSCHE");
		car.setModel("F40");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), true);
		
		car.setMake("PORSCHE");
		car.setModel("");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), true);
		
		car.setMake("");
		car.setModel("F40");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), true);
		
		car.setMake("");
		car.setModel("");
		assertEquals(errorValidation.checkMakeAndModelNotAllowed(car), true);
		
	}
	
	
}

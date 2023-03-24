package edu.tus.car.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.tus.car.dao.CarRepository;
import edu.tus.car.errors.ErrorValidation;
import edu.tus.car.model.Car;
import edu.tus.car.service.CarService;

@SpringBootTest
public class CarControllerTest {
	
	@Autowired
	CarController carController;
	
	@MockBean
	CarRepository carRepository;
	
	@MockBean
	CarService carService;
	
	@MockBean
	ErrorValidation errorValidation;
	
	@Test
	public void addCarTest() {
		Car bmw = buildBMW();
		when(errorValidation.checkMakeAndModelNotAllowed(bmw)).thenReturn(false);
		when(errorValidation.colorNotOK(bmw)).thenReturn(false);
		when(errorValidation.yearNotOK(bmw)).thenReturn(false);
		
		ResponseEntity response	= carController.addCar(bmw);
		assertEquals(response.getStatusCode(),HttpStatus.CREATED);
	}

	@Test
	public void deleteCarTest() {
		ResponseEntity response	= carController.deleteCar((long)1);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void getAllCarsTest() {
		ResponseEntity response	= carController.getAllCars();
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}
	
	
	
	public Car buildBMW() {
		Car car = new Car();
		car.setId((long)1);
		car.setMake("BMW");
		car.setModel("320");
		car.setYear(2021);
		car.setColor("Green");
		
		return car;
	}

}

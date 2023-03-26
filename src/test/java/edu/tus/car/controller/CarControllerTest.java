package edu.tus.car.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import edu.tus.car.dao.CarRepository;
import edu.tus.car.errors.ErrorMessages;
import edu.tus.car.errors.ErrorValidation;
import edu.tus.car.exception.CarException;
import edu.tus.car.exception.CarNotFoundException;
import edu.tus.car.exception.CarValidationException;
import edu.tus.car.model.Car;
import edu.tus.car.service.CarService;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {
	
	@Autowired
	CarController carController;
	
	@MockBean
	CarRepository carRepository;
	
	@MockBean
	CarService carService;
	
	@MockBean
	ErrorValidation errorValidation;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void addCarTest() throws CarValidationException {
		Car bmw = buildBMW();
		// Pass all validation and create successfully
		when(errorValidation.checkMakeAndModelNotAllowed(bmw)).thenReturn(false);
		when(errorValidation.colorNotOK(bmw)).thenReturn(false);
		when(errorValidation.yearNotOK(bmw)).thenReturn(false);
		ResponseEntity successResponse	= carController.addCar(bmw);
		assertEquals(successResponse.getStatusCode(),HttpStatus.CREATED);
		
		// Fail validation and fail to create.
		when(errorValidation.checkMakeAndModelNotAllowed(bmw)).thenReturn(true);
		when(carService.createCar(bmw)).thenThrow(new CarValidationException(ErrorMessages.INVALID_MAKE_MODEL.getMsg()));
		ResponseEntity failResponse	= carController.addCar(bmw);
		assertEquals(failResponse.getStatusCode(),HttpStatus.BAD_REQUEST);
	}

	@Test
	public void deleteCarTest() throws CarNotFoundException {		
		long id = 1;
		// Delete car that can be found.
		Optional<Car> bmw = Optional.of(buildBMW());
		ResponseEntity successResponse	= carController.deleteCar((long)1);
		when(carRepository.findById(id)).thenReturn(bmw);
		when(carService.getCarById(id)).thenReturn(bmw);
		System.out.println(successResponse.getStatusCodeValue());
		assertEquals(successResponse.getStatusCode(),HttpStatus.OK);
		
		// Attempt do delete car when it cannot be found.
		when(carRepository.findById(id)).thenThrow(new RuntimeException());
		doThrow(new CarNotFoundException("Car Not Found")).when(carService).deleteCar(id);
		ResponseEntity failResponse	= carController.deleteCar(id);
		assertEquals(failResponse.getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	// Use MockMvc for tests that require validating json.
	@Test
	public void getCarByIDTest() throws Exception {
		long id = 1;
		Optional<Car> bmw = Optional.of(buildBMW());
		
		when(carRepository.findById(id)).thenReturn(bmw);
		when(carService.getCarById(id)).thenReturn(bmw);
		
		// Get mock BMW.
		this.mockMvc.perform(get("/api/cars/1")).andExpect(status().isOk()).
			andExpect(jsonPath("$.make").value("BMW")).
			andExpect(jsonPath("$.model").value("320")).
			andExpect(jsonPath("$.year").value("2021")).
			andExpect(jsonPath("$.color").value("Green"));
		
		// Attempt to get non-existent car 2.
		this.mockMvc.perform(get("/api/cars/2")).andExpect(status().isNoContent());		
	}
	
	@Test
	public void getAllCarsTest() throws Exception{
		ArrayList<Car> cars = new ArrayList<Car>();
		cars.add(buildBMW());
		cars.add(buildMerc());
		when(carRepository.findAll()).thenReturn(cars);
		when(carService.getAllCars()).thenReturn(cars);
		
		// Get all mock cars.
		this.mockMvc.perform(get("/api/cars")).andExpect(status().isOk()).
			andExpect(jsonPath("$.length()", is(2))).
			andExpect(jsonPath("$.[0].make").value("BMW")).
			andExpect(jsonPath("$.[0].model").value("320")).
			andExpect(jsonPath("$.[0].year").value("2021")).
			andExpect(jsonPath("$.[0].color").value("Green")).
			andExpect(jsonPath("$.[1].make").value("Mercedes")).
			andExpect(jsonPath("$.[1].model").value("E220")).
			andExpect(jsonPath("$.[1].year").value("2021")).
			andExpect(jsonPath("$.[1].color").value("Red"));
		
		// Get all cars when none exist.
		cars.clear();
		this.mockMvc.perform(get("/api/cars")).andExpect(status().isNoContent()).
		andExpect(jsonPath("$.length()", is(0)));
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

	public Car buildMerc() {
		Car car = new Car();
		car.setId((long)2);
		car.setMake("Mercedes");
		car.setModel("E220");
		car.setYear(2021);
		car.setColor("Red");
		
		return car;
	}
}

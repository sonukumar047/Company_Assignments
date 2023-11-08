package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Car;
import com.masai.service.CarService;

@RestController
@RequestMapping("/car")
@CrossOrigin("*")
public class CarController {

	@Autowired
	private CarService carService;
	
	@PostMapping(value = "/addCar")
	public ResponseEntity<Car> saveCar(@RequestBody Car car){
		return new ResponseEntity<Car>(carService.saveCar(car), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getCarById/{carId}")
	public ResponseEntity<Car> getCarById(@PathVariable("carId") int carId){
		return new ResponseEntity<Car>(carService.getCarByCarId(carId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getByCarNumber/{carNumber}")
	public ResponseEntity<Car> getCarByCarNubmer(@PathVariable("carNumber") String carNumber){
		return new ResponseEntity<Car>(carService.getCarByCarNumber(carNumber), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getAllCar")
	public ResponseEntity<List<Car>> getAllCar(){
		return new ResponseEntity<List<Car>>(carService.getAllCar(), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/deleteCar/{carId}")
	public ResponseEntity<String> deleteCar(@PathVariable("carId") int carId){
		return new ResponseEntity<String>(carService.deleteCarByCarId(carId), HttpStatus.ACCEPTED);
	}
}

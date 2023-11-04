package com.masai.service;

import java.util.List;

import com.masai.model.Car;

public interface CarService {

	public Car saveCar(Car car);
	public Car getCarByCarId(int carId);
	public Car getCarByCarNumber(String carNumber);
	public List<Car> getAllCar();
	public String deleteCarByCarId(int carId);
}

package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Car;
import com.masai.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarRepository carRepository;

	@Override
	public Car saveCar(Car car) {
		return carRepository.save(car);
	}

	@Override
	public Car getCarByCarId(int carId) {
		return carRepository.findById(carId).get();
	}

	@Override
	public Car getCarByCarNumber(String carNumber) {
		return carRepository.findByCarNumber(carNumber);
	}

	@Override
	public List<Car> getAllCar() {
		return carRepository.findAll();
	}

	@Override
	public String deleteCarByCarId(int carId) {

		carRepository.deleteById(carId);
		return "Car deleted successfully";
	}

}

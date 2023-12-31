package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

	public Car findByCarNumber(String carNumber);
}

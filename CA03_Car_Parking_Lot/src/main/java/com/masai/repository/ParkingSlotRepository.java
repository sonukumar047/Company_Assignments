package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.ParkingSlot;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer> {

	ParkingSlot findBySlotNumber(String slotNumber);
//	ParkingSlot findByCarNumber(String carNumber);
}

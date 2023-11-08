package com.masai.service;

import java.util.List;

import com.masai.model.Car;
import com.masai.model.ParkingSlot;

public interface ParkingSlotService {

//	public ParkingSlot parkCar(ParkingSlot parkingSlot);
	public ParkingSlot parkCar(String carNumber);
	public ParkingSlot getParkingDetailsBySlotId(int slotId);
//	public ParkingSlot getParkingDetailsBySlotNumber(String slotNumber);
	public List<ParkingSlot> getAllParkingDetails();
	public String unparkCar(int slotId);
}

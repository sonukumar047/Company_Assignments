package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.ParkingSlotNotFound;
import com.masai.model.Car;
import com.masai.model.ParkingSlot;
import com.masai.repository.CarRepository;
import com.masai.repository.ParkingSlotRepository;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService{
	
	@Autowired
	private ParkingSlotRepository parkingSlotRepository;
	
	@Autowired
	private CarRepository carRepository;

	private int getMaxParkingLotSize() {
	    int maxParkingLotSize = 50;
	    return maxParkingLotSize;
	}

	private ParkingSlot findEmptyParkingSlot() {
	    for (int slotNumber = 1; slotNumber <= getMaxParkingLotSize(); slotNumber++) {
	        Optional<ParkingSlot> slot = parkingSlotRepository.findById(slotNumber);
	        if (slot.isEmpty()) {
	        	System.out.println("Slot number!"+slotNumber);
	            return new ParkingSlot(); // Assuming ParkingSlot constructor takes slot number as a parameter.
	        }
	    }
	    throw new ParkingSlotNotFound("Parking slots are full!");
	}

//	@Override
//	public ParkingSlot parkCar(ParkingSlot parkingSlot) {
////	    ParkingSlot emptySlot = findEmptyParkingSlot();
////	    if (emptySlot != null) {
//	        return parkingSlotRepository.save(parkingSlot);
////	    } else {
////	        throw new ParkingSlotNotFound("Can't Park");
////	    }
//	}
	
	@Override
	public ParkingSlot parkCar(String carNumber) {
		System.out.println("Inside Parking Lot");
		 Car parkCar = carRepository.findByCarNumber(carNumber);
		 System.out.println(parkCar);
		 ParkingSlot parkingSlot = new ParkingSlot();
		 parkingSlot.setParkingTime(LocalDateTime.now());
		 parkingSlot.setCar(parkCar);
		 
		return parkingSlotRepository.save(parkingSlot);
	}


	@Override
	public ParkingSlot getParkingDetailsBySlotId(int slotId) {
//		Optional<ParkingSlot> findById = parkingSlotRepository.findById(slotId);
//		return findById.get();
		return parkingSlotRepository.findById(slotId).get();
	}

//	@Override
//	public ParkingSlot getParkingDetailsBySlotNumber(String slotNumber) {
//		
//		return parkingSlotRepository.findBySlotNumber(slotNumber);
//	}

	@Override
	public List<ParkingSlot> getAllParkingDetails() {
		// TODO Auto-generated method stub
		return parkingSlotRepository.findAll();
	}

	@Override
	public String unparkCar(int slotId) {
		parkingSlotRepository.deleteById(slotId);
		return "Car unparked successfully";
	}

}

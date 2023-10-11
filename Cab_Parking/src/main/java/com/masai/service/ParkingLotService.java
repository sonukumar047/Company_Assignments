package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomException;
import com.masai.exception.ParkingLotFullException;
import com.masai.model.ParkingLot;
import com.masai.repository.ParkingLotRepository;

@Service
public class ParkingLotService {

	@Autowired
    private  ParkingLotRepository parkingLotRepository;

//    @Autowired
//    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
//        this.parkingLotRepository = parkingLotRepository;
//    }

    public ParkingLot parkCar(String carNumber) {
        // Check if the parking lot is full (you can modify the logic based on your requirements)
        // For example, if parking lot size is fixed at 50:
        if (parkingLotRepository.count() >= 50) {
            throw new ParkingLotFullException("Parking lot is full. Cannot park more cars.");
        }

        // Check if the car is already parked
        Optional<ParkingLot> existingParkingLotEntry = parkingLotRepository.findByCarNumber(carNumber);
        if (existingParkingLotEntry.isPresent()) {
            return existingParkingLotEntry.get();
        }

        // Find an available slot (you can implement your own logic here)
        // For example, finding the first available slot:
        Integer availableSlot = parkingLotRepository.findFirstAvailableSlot();
        System.out.println(availableSlot);
        if (availableSlot == null) {
            throw new CustomException("No available slots in the parking lot.");
        }

        // Park the car in the available slot
        ParkingLot parkingLot = new ParkingLot(carNumber, availableSlot);
        return parkingLotRepository.save(parkingLot);
    }

    public void unparkCar(Integer slotNumber) {
        Optional<ParkingLot> parkingLotEntry = parkingLotRepository.findBySlotNumber(slotNumber);
        if (parkingLotEntry.isPresent()) {
            parkingLotRepository.delete(parkingLotEntry.get());
        } else {
            throw new CustomException("No car found at the specified slot number.");
        }
    }

    public ParkingLot getCarOrSlotInfo(String input) {
        try {
            Integer slotNumber = Integer.parseInt(input);
            return parkingLotRepository.findBySlotNumber(slotNumber)
                    .orElseThrow(() -> new CustomException("No car found at the specified slot number."));
        } catch (NumberFormatException e) {
            return parkingLotRepository.findByCarNumber(input)
                    .orElseThrow(() -> new CustomException("No car found with the specified car number."));
        }
    }
}

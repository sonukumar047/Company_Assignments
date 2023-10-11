package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomException;
import com.masai.exception.ParkingLotFullException;
import com.masai.model.ParkingLot;
import com.masai.service.ParkingLotService;

@RestController
@RequestMapping("/parking")
public class ParkingLotController {

	@Autowired
    private  ParkingLotService parkingLotService;

//    @Autowired
//    public ParkingLotController(ParkingLotService parkingLotService) {
//        this.parkingLotService = parkingLotService;
//    }

    @PostMapping("/park")
    public ResponseEntity<?> parkCar(@RequestParam String carNumber) {
        try {
            ParkingLot parkedCar = parkingLotService.parkCar(carNumber);
            return ResponseEntity.status(HttpStatus.CREATED).body(parkedCar);
        } catch (ParkingLotFullException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/unpark")
    public ResponseEntity<?> unparkCar(@RequestParam Integer slotNumber) {
        try {
            parkingLotService.unparkCar(slotNumber);
            return ResponseEntity.status(HttpStatus.OK).body("Car successfully unparked from slot " + slotNumber);
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/info")
    public ResponseEntity<?> getCarOrSlotInfo(@RequestParam String input) {
        try {
            ParkingLot carInfo = parkingLotService.getCarOrSlotInfo(input);
            return ResponseEntity.status(HttpStatus.OK).body(carInfo);
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

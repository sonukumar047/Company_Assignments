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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Car;
import com.masai.model.ParkingSlot;
import com.masai.service.ParkingSlotService;

@RestController
@RequestMapping("/parking")
@CrossOrigin("*")
public class ParkingSlotController {
	
	@Autowired
	private ParkingSlotService parkingSlotService;
	
//	@PostMapping(value = "/park")
//	public ResponseEntity<ParkingSlot> parkCar(@RequestBody ParkingSlot parkingSlot){
//		return new ResponseEntity<ParkingSlot>(parkingSlotService.parkCar(parkingSlot), HttpStatus.ACCEPTED);
//	}
	
	@PostMapping("/park/{carNumber}")
    public ResponseEntity<ParkingSlot> parkCar(@PathVariable String carNumber) {
        return new ResponseEntity<ParkingSlot>(parkingSlotService.parkCar(carNumber), HttpStatus.ACCEPTED);
        
    }
	
	@GetMapping(value = "/getParkBySlotId/{slotId}")
	public ResponseEntity<ParkingSlot> getParkingDetailsBySlotId(@PathVariable("slotId") int slotId){
		return new ResponseEntity<ParkingSlot>(parkingSlotService.getParkingDetailsBySlotId(slotId), HttpStatus.ACCEPTED);
	}
	
//	@GetMapping(value = "/getParkBySlotNumber/{slotNumber}")
//	public ResponseEntity<ParkingSlot> getParkingDetailsBySlotNumber(@PathVariable("slotNumber") String slotNumber){
//		return new ResponseEntity<ParkingSlot>(parkingSlotService.getParkingDetailsBySlotNumber(slotNumber), HttpStatus.ACCEPTED);
//	}
	
	@GetMapping(value = "/getAllParkingSlot")
	public ResponseEntity<List<ParkingSlot>> getAllParkingSlotDetails(){
		return new ResponseEntity<List<ParkingSlot>>(parkingSlotService.getAllParkingDetails(), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/unpark/{slotId}")
	public ResponseEntity<String> unparkCar(@PathVariable("slotId") int slotId){
		return new ResponseEntity<String>(parkingSlotService.unparkCar(slotId), HttpStatus.ACCEPTED);
	}

}

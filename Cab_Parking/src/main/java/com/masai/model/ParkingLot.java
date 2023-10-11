package com.masai.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_lot")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingLot {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_number", nullable = false, unique = true)
    private String carNumber;

    @Column(name = "slot_number", nullable = false, unique = true)
    private Integer slotNumber=50;

	public ParkingLot(String carNumber, Integer slotNumber) {
		super();
		this.carNumber = carNumber;
		this.slotNumber = slotNumber;
	}
    
}

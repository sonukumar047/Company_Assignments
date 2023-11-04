package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity
@Data
@Embeddable
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carId;
	private String carNumber;
	private String carName;
	
	@JsonIgnore
	@OneToOne(mappedBy = "car")
	private ParkingSlot parkingSlot;
}

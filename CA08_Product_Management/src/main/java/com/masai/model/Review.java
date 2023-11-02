package com.masai.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewId;
	private String userId;
	private String description;
	private LocalDate cDate = LocalDate.now();
	private LocalDate uDate = LocalDate.now();
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "product_id")
	private Product product;
	
}

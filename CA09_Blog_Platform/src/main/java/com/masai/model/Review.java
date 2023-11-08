package com.masai.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;
	
	private String userId;
	private String description;
	private LocalDateTime cDate = LocalDateTime.now();
	private LocalDateTime uDate = LocalDateTime.now();
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "blogId")
	private Blog blog;
}

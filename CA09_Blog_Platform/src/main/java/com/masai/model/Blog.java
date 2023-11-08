package com.masai.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blogId;
	
	private String title;
	private String body;
	private LocalDateTime cDate = LocalDateTime.now();
	private LocalDateTime uDate = LocalDateTime.now();
	
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();
	
}

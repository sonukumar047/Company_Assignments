package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

}

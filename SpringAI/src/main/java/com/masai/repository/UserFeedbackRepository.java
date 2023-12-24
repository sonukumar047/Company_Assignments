package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.UserFeedback;

public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Integer>{

}

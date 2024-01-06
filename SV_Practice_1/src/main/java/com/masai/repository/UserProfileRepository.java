package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

}

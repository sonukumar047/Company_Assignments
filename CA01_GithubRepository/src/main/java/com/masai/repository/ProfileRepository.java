package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}

package com.masai.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Repository;

public interface ReposRepository extends JpaRepository<Repository, String>{

}

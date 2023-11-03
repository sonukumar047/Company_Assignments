package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Repository;

public interface RepoRepository extends JpaRepository<Repository, String> {

}

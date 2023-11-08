package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

}

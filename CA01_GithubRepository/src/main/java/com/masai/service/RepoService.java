package com.masai.service;

import java.util.List;

import com.masai.model.Repository;

public interface RepoService {

	public List<Repository> getAllRepository(String username);
	public List<Repository> saveAllRepository(String username);
	public List<Repository> getAllSavedRepository();
	public Repository getSavedRepositoryById(String id);
}

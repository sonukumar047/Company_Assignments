package com.masai.service;

import java.util.List;

import com.masai.model.Repository;

public interface RepoService {

	public List<Repository> getAllRepo(String username);
	public List<Repository> saveAllRepo(String username);
	public List<Repository> getAllSavedRepo();
	public Repository getSavedRepoById(String id);
}

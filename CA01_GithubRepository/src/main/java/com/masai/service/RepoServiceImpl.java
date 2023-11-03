package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.masai.exception.RepositoryNotFound;
import com.masai.model.Repository;
import com.masai.repository.RepoRepository;

@Service
public class RepoServiceImpl implements RepoService{
	
	private static String reposUrl = "https://api.github.com/users/";
	
	@Autowired
	private RepoRepository repoRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Repository> getAllRepository(String username) {
		
		ResponseEntity<List<Repository>> repoList = restTemplate
				.exchange(
						reposUrl+username+"/repos", 
						HttpMethod.GET, 
						null, 
						new ParameterizedTypeReference<List<Repository>>(){});
		
		List<Repository> repositories = repoList.getBody();
		
		if(!repositories.isEmpty()) {
			return repositories;
		}
		else throw new RepositoryNotFound("Repository not found!");
	}

	@Override
	public List<Repository> saveAllRepository(String username) {
		ResponseEntity<List<Repository>> repoList = restTemplate
				.exchange(
						reposUrl+username+"/repos", 
						HttpMethod.GET, 
						null, 
						new ParameterizedTypeReference<List<Repository>>(){});
		
		List<Repository> repositories = repoList.getBody();
		
		if(!repositories.isEmpty()) {
			return repoRepository.saveAll(repositories);
		}
		else throw new RepositoryNotFound("Repository not found!");
	}

	@Override
	public List<Repository> getAllSavedRepository() {
		List<Repository> repoList = repoRepository.findAll();
		
		if(!repoList.isEmpty()) {
			return repoList;
		}
		else throw new RepositoryNotFound("Repository not found!");
	}

	@Override
	public Repository getSavedRepositoryById(String id) {
		return repoRepository.findById(id).orElseThrow(
				()-> new RepositoryNotFound("Repository not found!"));
	}

}

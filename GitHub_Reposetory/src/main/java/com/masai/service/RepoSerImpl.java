package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.masai.exception.RepositoryNotFoundException;
import com.masai.model.Repository;
import com.masai.repo.ReposRepository;

@Service
public class RepoSerImpl implements RepoService{

	@Autowired
	private ReposRepository reposRepository;
	
	private static String reposUrl = "https://api.github.com/users/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Repository> getAllRepo(String username) {
		
		ResponseEntity<List<Repository>> responseEntity = restTemplate
				.exchange(
						reposUrl+username+"/repos",
						HttpMethod.GET,
						null,
						new ParameterizedTypeReference<List<Repository>>(){}
						);
		List<Repository> repos = responseEntity.getBody();
		
		if(!repos.isEmpty()) {
			return repos;
		}
		else throw new RepositoryNotFoundException("Repository not found");
	}

	@Override
	public List<Repository> saveAllRepo(String username) {
		
		ResponseEntity<List<Repository>> responseEntity = restTemplate
				.exchange(
						reposUrl+username+"/repos",
						HttpMethod.GET,
						null,
						new ParameterizedTypeReference<List<Repository>>(){}
						);
		List<Repository> repos = responseEntity.getBody();
		
		if(!repos.isEmpty()) {
			return reposRepository.saveAll(repos);
		}
		else throw new RepositoryNotFoundException("Repository not found");
	}

	@Override
	public List<Repository> getAllSavedRepo() {
		List<Repository> reposList = reposRepository.findAll();
		
		if(reposList.isEmpty()) throw new RepositoryNotFoundException("Repository not found");
		return reposList;
		
	}

	@Override
	public Repository getSavedRepoById(String id) {
		return reposRepository.findById(id).orElseThrow(
				() -> new RepositoryNotFoundException("Repository not found")
				);
	}

}

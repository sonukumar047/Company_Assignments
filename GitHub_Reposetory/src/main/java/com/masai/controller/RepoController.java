package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Repository;
import com.masai.service.RepoService;

@RestController
public class RepoController {

	@Autowired
	private RepoService repoService;
	
	@GetMapping(value = "/userRepos/{username}")
	public ResponseEntity<List<Repository>> getAllRepo(@PathVariable("username") String username){
		return new ResponseEntity<List<Repository>>(repoService.getAllRepo(username), HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "/userRepos/{username}")
	public ResponseEntity<List<Repository>> saveAllRepo(@PathVariable("username")String username){
		return new ResponseEntity<>(repoService.saveAllRepo(username), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/savedRepos")
	public ResponseEntity<List<Repository>> getAllSavedRepo(){
		return new ResponseEntity<>(repoService.getAllSavedRepo(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/savedRepos/{id}")
	public ResponseEntity<Repository> getSavedRepoById(@PathVariable("id")String id){
		return new ResponseEntity<Repository>(repoService.getSavedRepoById(id), HttpStatus.ACCEPTED);
	}
}

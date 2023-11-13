package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Repository;
import com.masai.service.RepoService;

@RestController
@CrossOrigin("*")
public class RepoController {

	@Autowired
	private RepoService repoService;
	
	@GetMapping(value = "/userRepos/{username}")
	public ResponseEntity<List<Repository>> getAllRepository(@PathVariable("username") String username){
		return new ResponseEntity<List<Repository>>(repoService.getAllRepository(username), HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "/userRepos/{username}")
	public ResponseEntity<List<Repository>> saveAllRepository(@PathVariable("username") String username){
		return new ResponseEntity<List<Repository>>(repoService.saveAllRepository(username), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/savedRepos")
	public ResponseEntity<List<Repository>> getAllSavedRepository(){
		return new ResponseEntity<List<Repository>>(repoService.getAllSavedRepository(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/savedRepos/{id}")
	public ResponseEntity<Repository> getSavedRepositoryById(@PathVariable("id") String id){
		return new ResponseEntity<Repository>(repoService.getSavedRepositoryById(id), HttpStatus.ACCEPTED);
	}
}

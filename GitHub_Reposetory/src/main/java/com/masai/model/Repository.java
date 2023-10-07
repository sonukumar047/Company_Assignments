package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "repository")
public class Repository {

	@Id
	private String id;
	private String name;
	private String html_url;
	private String description;
	private String created_at;
	private String open_issues;
	private String watchers;
	private String clone_url;
	
	@Embedded
	private Owner owner;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_profile_id")
	private Profile profile;
	
	
}

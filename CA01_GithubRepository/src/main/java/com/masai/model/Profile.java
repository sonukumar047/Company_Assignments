package com.masai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "git_profile")
public class Profile {

	@Id
	private String id;
	private String name;
	private String avatar_url;
	private String html_url;
	private String type;
	private String site_admin;
	private String bio;
	private String location;
	
	@JsonIgnore
	@OneToMany(mappedBy = "profile")
	private List<Repository> repositories;
}

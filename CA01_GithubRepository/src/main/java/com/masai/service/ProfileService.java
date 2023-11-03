package com.masai.service;

import java.util.List;

import com.masai.model.Profile;

public interface ProfileService {

	public Profile getProfile(String username);
	public Profile saveProfile(String username);
	public Profile getSavedProfile(String id);
	public List<Profile> getAllSavedFrofile();
}

package com.masai.service;

import com.masai.model.Profile;

public interface ProfileService {

	public Profile getProfile(String username);
	public Profile saveProfile(String username);
	public Profile getSavedProfile(String id);
	public Profile getAllSavedProfile();
}

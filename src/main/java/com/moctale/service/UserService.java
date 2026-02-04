package com.moctale.service;

import com.moctale.models.User;

public interface UserService {
	User getUserById(Long id);
	
	User getUserByUsername(String username);
	
	User updateProfile(Long userId, String bio, byte[] avatarImage);
}

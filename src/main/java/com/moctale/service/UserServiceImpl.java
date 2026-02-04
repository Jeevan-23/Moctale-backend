package com.moctale.service;

import org.springframework.stereotype.Service;

import com.moctale.models.User;
import com.moctale.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
	}
	

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateProfile(Long userId, String bio, byte[] avatarImage) {
        User user = getUserById(userId);
        user.setBio(bio);
        user.setAvatarImage(avatarImage);
        return userRepository.save(user);
    }
	
}

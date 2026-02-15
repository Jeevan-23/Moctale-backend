package com.moctale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moctale.dto.ProfileUpdateRequest;
import com.moctale.dto.UserProfileResponse;
import com.moctale.mapper.UserMapper;
import com.moctale.models.User;
import com.moctale.security.SecurityUtils;
import com.moctale.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService usr) {
		this.userService = usr;
	}
	
	@GetMapping("/{id}")
	public UserProfileResponse getProfile(@PathVariable Long id) {
		return UserMapper.toProfileResponse(userService.getUserById(id));
	}
	
	@PutMapping("/{id}")
	public UserProfileResponse updateProfile( 
			@RequestBody @Valid ProfileUpdateRequest request) {
		Long id = SecurityUtils.getCurrentUserId();
		User user = userService.updateProfile(id, request.getBio(),  null);
		return UserMapper.toProfileResponse(user);
	}
}

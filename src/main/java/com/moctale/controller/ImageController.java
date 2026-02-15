package com.moctale.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.moctale.models.Content;
import com.moctale.models.User;
import com.moctale.service.ContentService;
import com.moctale.service.UserService;

@RestController
@RequestMapping("/api/images")
public class ImageController {
	
	private final ContentService contentService;
	private final UserService userService;
	
	public ImageController(ContentService contentService, UserService userService) {
		this.contentService = contentService;
		this.userService = userService;
	}
	
//	______________________Content Poster________________________________
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/content/{contentId}/poster", 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> uploadPoster(
			@PathVariable Long contentId,
			@RequestParam("image") MultipartFile file) 
	throws IOException {
		
		validateImage(file);
		
		Content content = contentService.getContentById(contentId);
		content.setPosterImage(file.getBytes());
		contentService.addContent(content);
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("/content/{contentId}/poster")
	public ResponseEntity<byte[]> getPoster(@PathVariable Long contentId) {
		
		Content content = contentService.getContentById(contentId);
		byte[] image = content.getPosterImage();
		if(image == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(image);
	}
	
//	--------------------USER Avatar --------------------------
	
	@PostMapping(value = "/users/{userId}/avatar", 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> uploadAvatar(@PathVariable Long userId, 
			@RequestParam("image") MultipartFile file) throws IOException{
		
		validateImage(file);
		userService.updateProfile(userId, null, file.getBytes());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/users/{userId}/avatar")
	public ResponseEntity<byte[]> getAvatar(@PathVariable Long userId) {
		User user = userService.getUserById(userId);
		byte[] image = user.getAvatarImage();
		
		if(image == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(image);
	}
	
//	-------------------Validation----------------------------
	
	private void validateImage(MultipartFile file) {
		if(file.isEmpty()) {
			throw new IllegalArgumentException("Image is empty");
		}
		
		if(file.getSize() > 2 * 1024 * 1024) {
			throw new IllegalArgumentException("Image size must be < 2 MB");
		}
		
		String contentType = file.getContentType();
		 if (contentType == null ||
		            (!contentType.equals("image/jpeg")
		             && !contentType.equals("image/png"))) {
		            throw new IllegalArgumentException("Only JPG/PNG allowed");
		        }
	}
	
	
}
	
	
package com.moctale.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moctale.dto.ReviewCreateRequest;
import com.moctale.dto.ReviewResponse;
import com.moctale.mapper.ReviewMapper;
import com.moctale.models.Review;
import com.moctale.repository.ReviewLikeRepository;
import com.moctale.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/content/{contentId}/reviews")
public class ReviewController {
	
	private final ReviewService reviewService;
	private final ReviewLikeRepository reviewLikeRepository;
	
	public ReviewController(ReviewService reviewService, 
			ReviewLikeRepository reviewLikeRepository) {
		this.reviewService = reviewService;
		this.reviewLikeRepository = reviewLikeRepository;
	}
	
	@PostMapping
	public ReviewResponse addReview(@PathVariable Long contentId, 
			@RequestBody @Valid ReviewCreateRequest request,
			@RequestParam Long userId) {
		Review review = reviewService.addReview(userId, contentId, request.getReviewType(), request.getComment());
		long likes = reviewLikeRepository.countByReviewId(review.getId());
		return ReviewMapper.toResponse(review, likes);
	}
	
	@GetMapping
	public List<ReviewResponse> getReviews(@PathVariable Long contentId) {
		return reviewService.getReviewsForContent(contentId)
				.stream()
						.map(r -> ReviewMapper.toResponse(r, 
						reviewLikeRepository.countByReviewId(r.getId())))
				.toList();
	}
	
}

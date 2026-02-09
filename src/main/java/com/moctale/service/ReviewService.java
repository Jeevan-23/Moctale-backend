package com.moctale.service;

import java.util.List;
import java.util.Map;

import com.moctale.models.Review;
import com.moctale.models.ReviewType;

public interface ReviewService {
	Review addReview(Long userId, Long contentId, ReviewType type, String comment);

	List<Review> getReviewsForContent(Long contentId);
	
	Map<ReviewType, Long> getReviewDistribution(Long contentId);
	
	List<Review> getReviewsByUser(Long userId);
}

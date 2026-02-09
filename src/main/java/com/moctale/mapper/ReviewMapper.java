package com.moctale.mapper;

import com.moctale.dto.ReviewResponse;
import com.moctale.models.Review;

public class ReviewMapper {
	
	private ReviewMapper() {}
	
	public static ReviewResponse toResponse(Review review, long likeCount) {
		ReviewResponse dto = new ReviewResponse();
		dto.setId(review.getId());
        dto.setUsername(review.getUser().getUsername());
        dto.setReviewType(review.getReviewType());
        dto.setCommentText(review.getCommentText());
        dto.setLikeCount(likeCount);
        dto.setCreatedAt(review.getCreatedAt());
        return dto;

	}
}

package com.moctale.service;

public interface ReviewLikeService {
	
	void likeReview(Long userId, Long reviewId);
	
	void unlikeReview(Long userId, Long reviewId);
	
	long getLikecount(Long reviewId);
}

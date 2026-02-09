package com.moctale.service;

import java.util.Optional;

import com.moctale.models.Review;
import com.moctale.models.ReviewLike;
import com.moctale.models.User;
import com.moctale.repository.ReviewLikeRepository;
import com.moctale.repository.ReviewRepository;
import com.moctale.repository.UserRepository;

public class ReviewLikeServiceImpl implements ReviewLikeService {
	
	private final ReviewLikeRepository reviewLikeRepository;
	private final UserRepository userRepository;
	private final ReviewRepository reviewRepository;
	
	public ReviewLikeServiceImpl(
			ReviewLikeRepository r, UserRepository u, ReviewRepository rr) {
		this.reviewLikeRepository = r;
		this.userRepository= u;
		this.reviewRepository = rr;
	}

	@Override
	public void likeReview(Long userId, Long reviewId) {
		if(reviewLikeRepository.existsByUserIdAndReviewId(userId, reviewId)) {
			return;
		}
		User user = userRepository.findById(userId).
				orElseThrow(() -> new RuntimeException("User not found"));
		
		Review review = reviewRepository.findById(reviewId).orElseThrow(()
			-> new RuntimeException("Review not found"));
		
		ReviewLike like = new ReviewLike();
		like.setUser(user);
		like.setReview(review);
		reviewLikeRepository.save(like);
	}

	@Override
	public void unlikeReview(Long userId, Long reviewId) {
//		reviewLikeRepository.findAll().stream()
//		.filter(l -> l.getUser().getId().equals(userId) && 
//				l.getReview().getId().equals(reviewId))
//		.findFirst()
//		.ifPresent(reviewLikeRepository::delete);
		Optional<ReviewLike> like = reviewLikeRepository.findByUserIdAndReviewId(userId, reviewId);
		like.ifPresent(reviewLikeRepository::delete);

		
	}

	@Override
	public long getLikecount(Long reviewId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}

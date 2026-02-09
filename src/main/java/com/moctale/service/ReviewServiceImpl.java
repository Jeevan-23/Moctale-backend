package com.moctale.service;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.moctale.models.Content;
import com.moctale.models.Review;
import com.moctale.models.ReviewType;
import com.moctale.models.User;
import com.moctale.repository.ContentRepository;
import com.moctale.repository.ReviewRepository;
import com.moctale.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{

	
	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final ContentRepository contentRepository;
	
	public ReviewServiceImpl(
			ReviewRepository rp, UserRepository up,
			ContentRepository cp) {
		this.contentRepository = cp;
		this.userRepository = up;
		this.reviewRepository = rp;
	}
	
	
	
	@Override
	public Review addReview(Long userId, Long contentId, ReviewType type, String comment) {
		reviewRepository.findByUserIdAndContentId(userId, contentId)
		.ifPresent(r -> {
			throw new RuntimeException("User already reviewd this content");
		});
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
	
		Content content = contentRepository.findById(contentId)
				.orElseThrow(() -> new RuntimeException("content not found"));
		
		Review review = new Review();
		review.setUser(user);
		review.setContent(content);
		review.setReviewType(type);
		review.setCommentText(comment);
		review.setCreatedAt(LocalDateTime.now());
		review.setUpdatedAt(LocalDateTime.now());
		
		return reviewRepository.save(review);
	
	}

	@Override
	public List<Review> getReviewsForContent(Long contentId) {
		return reviewRepository.findByContentId(contentId);
	}

	@Override
	public Map<ReviewType, Long> getReviewDistribution(Long contentId) {
		 Map<ReviewType, Long> distribution = new EnumMap<>(ReviewType.class);

	        for (ReviewType type : ReviewType.values()) {
	            long count = reviewRepository
	                    .countByContentIdAndReviewType(contentId, type);
	            distribution.put(type, count);
	        }

	        return distribution;
	}
	
	@Override
	public List<Review> getReviewsByUser(Long userId) {
	    return reviewRepository.findByUserId(userId);
	}


}

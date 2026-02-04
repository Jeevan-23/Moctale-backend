package com.moctale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.moctale.models.ReviewLike; 

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
	
	boolean existsByUserIdAndReviewId(Long userId, Long reviewId);
	
	long countByReviewId(Long reviewId);
	
}

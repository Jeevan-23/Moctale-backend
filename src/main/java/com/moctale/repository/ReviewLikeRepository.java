package com.moctale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.moctale.models.ReviewLike; 

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
	
	boolean existsByUserIdAndReviewId(Long userId, Long reviewId);
	
	long countByReviewId(Long reviewId);
	
	Optional<ReviewLike> findByUserIdAndReviewId(Long userId, Long reviewId);
	
}

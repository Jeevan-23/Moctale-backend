package com.moctale.repository;

import java.util.List;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moctale.models.ReviewLike; 

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
	
	boolean existsByUserIdAndReviewId(Long userId, Long reviewId);
	
	long countByReviewId(Long reviewId);
	
	Optional<ReviewLike> findByUserIdAndReviewId(Long userId, Long reviewId);
	
	@Query("""
		    SELECT rl.review.id, COUNT(rl)
		    FROM ReviewLike rl
		    GROUP BY rl.review.id
		    ORDER BY COUNT(rl) DESC
		""")
		List<Object[]> findMostLikedReviews(Pageable pageable);

}

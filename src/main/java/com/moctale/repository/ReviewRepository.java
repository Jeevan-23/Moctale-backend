package com.moctale.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.moctale.models.Review;
import com.moctale.models.ReviewType;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByUserIdAndContentId(Long userId, Long contentId);
	
	List<Review> findByContentId(Long contentId);
	
	long countByContentId(Long contentid);
	
	long countByContentIdAndReviewType(Long contentId, ReviewType reviewType);
	
	List<Review> findByUserId(Long userId);

}

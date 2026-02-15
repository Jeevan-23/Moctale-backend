package com.moctale.repository;

import java.util.Optional;


//import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moctale.models.Review;
import com.moctale.models.ReviewType;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByUserIdAndContentId(Long userId, Long contentId);
	
	List<Review> findByContentId(Long contentId);
	
	long countByContentId(Long contentid);
	
	long countByContentIdAndReviewType(Long contentId, ReviewType reviewType);
	
	List<Review> findByUserId(Long userId);
	
	@Query("""
		    SELECT r.content.id, COUNT(r)
		    FROM Review r
		    GROUP BY r.content.id
		    ORDER BY COUNT(r) DESC
		""")
		List<Object[]> findMostReviewedContent(Pageable pageable);
		
		
		@Query("""
			    SELECT r.content.id, COUNT(r)
			    FROM Review r
			    WHERE r.createdAt >= :date
			    GROUP BY r.content.id
			    ORDER BY COUNT(r) DESC
			""")
			List<Object[]> findTrendingContent(
			        @Param("date") LocalDateTime date,
			        Pageable pageable
			);

}

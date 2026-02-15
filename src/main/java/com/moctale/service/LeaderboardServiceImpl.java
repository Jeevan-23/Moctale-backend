package com.moctale.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
//import org.springdoc.core.converters.models.Pageable;

import com.moctale.dto.ContentResponse;
import com.moctale.dto.ReviewResponse;
import com.moctale.mapper.ContentMapper;
import com.moctale.mapper.ReviewMapper;
import com.moctale.models.Review;
import com.moctale.repository.ContentRepository;
import com.moctale.repository.ReviewLikeRepository;
import com.moctale.repository.ReviewRepository;

@Service
@Transactional(readOnly = true)
public class LeaderboardServiceImpl implements LeaderboardService {

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final ContentRepository contentRepository;

    public LeaderboardServiceImpl(
            ReviewRepository reviewRepository,
            ReviewLikeRepository reviewLikeRepository,
            ContentRepository contentRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewLikeRepository = reviewLikeRepository;
        this.contentRepository = contentRepository;
    }
    
    @Override
    public List<ContentResponse> mostReviewed(int limit) {
    	Pageable pageable = PageRequest.of(0, limit);
//    	LocalDateTime lastWeek = LocalDateTime.now().minusDays(7);
    	
    	return reviewRepository.findMostReviewedContent(pageable)
    			.stream()
    			.map(obj -> contentRepository
    					.findById((Long) obj[0])
    					.orElseThrow())
    			.map(ContentMapper::toResponse)
    			.toList();
    	
    }
    
    @Override
    public List<ContentResponse> trending(int limit) {

        Pageable pageable = PageRequest.of(0, limit);

        LocalDateTime lastWeek =
                LocalDateTime.now().minusDays(7);

        return reviewRepository
                .findTrendingContent(lastWeek, pageable)
                .stream()
                .map(obj -> contentRepository
                        .findById((Long) obj[0])
                        .orElseThrow())
                .map(ContentMapper::toResponse)
                .toList();
    }
    
    @Override
    public List<ReviewResponse> mostLikedReviews(int limit) {

        Pageable pageable = PageRequest.of(0, limit);

        return reviewLikeRepository
                .findMostLikedReviews(pageable)
                .stream()
                .map((Object[] obj) -> {

                    Long reviewId = ((Number) obj[0]).longValue();
                    Long likeCount = ((Number) obj[1]).longValue();

                    Review review = reviewRepository
                            .findById(reviewId)
                            .orElseThrow();

                    return ReviewMapper.toResponse(review, likeCount);
                })
                .toList();
    }

    
    
}
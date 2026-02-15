package com.moctale.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moctale.dto.CommentResponse;
import com.moctale.dto.ReviewCommentRequest;
import com.moctale.mapper.CommentMapper;
import com.moctale.security.SecurityUtils;
import com.moctale.service.ReviewCommentService;
import com.moctale.service.ReviewLikeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reviews/{reviewId}")
public class ReviewInteractionController {
	
	private final ReviewLikeService reviewLikeService;
	private final ReviewCommentService reviewCommentService;
	
	public ReviewInteractionController(
            ReviewLikeService reviewLikeService,
            ReviewCommentService reviewCommentService) {
        this.reviewLikeService = reviewLikeService;
        this.reviewCommentService = reviewCommentService;
    }
	
//	---------------like..................
	
	@PostMapping("/like")
	public ResponseEntity<Void> likeReview(
	        @PathVariable Long reviewId
	) {
	    Long userId = SecurityUtils.getCurrentUserId();
	    reviewLikeService.likeReview(userId, reviewId);
	    return ResponseEntity.ok().build();
	}

	
	@DeleteMapping("/like")
	public ResponseEntity<Void> unlikeReview(
            @PathVariable Long reviewId
    ) {
		Long userId = SecurityUtils.getCurrentUserId();
	    reviewLikeService.unlikeReview(userId, reviewId);
	    return ResponseEntity.ok().build();
    }
	
//	....................comments......................
	
	@PostMapping("/comments")
	public CommentResponse addComment(
            @PathVariable Long reviewId,
            @RequestBody @Valid ReviewCommentRequest request
    ) {
		Long userId = SecurityUtils.getCurrentUserId();
        return CommentMapper.toResponse(
                reviewCommentService.addComment(
                        userId,
                        reviewId,
                        request.getCommentText()
                )
        );
    }
	
	@GetMapping("/comments")
	public List<CommentResponse> getComments(@PathVariable Long reviewId) {
		return reviewCommentService.getComments(reviewId)
				.stream()
				.map(CommentMapper::toResponse)
				.toList();
	}
	
}

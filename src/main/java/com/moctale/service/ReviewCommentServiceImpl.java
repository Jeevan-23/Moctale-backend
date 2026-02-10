package com.moctale.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moctale.models.User;
import com.moctale.models.Review;
import com.moctale.models.ReviewComment;
import com.moctale.repository.ReviewCommentRepository;
import com.moctale.repository.ReviewRepository;
import com.moctale.repository.UserRepository;


@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {

	private final ReviewCommentRepository commentRepository;
	private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    
    public ReviewCommentServiceImpl(
            ReviewCommentRepository commentRepository,
            UserRepository userRepository,
            ReviewRepository reviewRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }
    
	@Override
	public ReviewComment addComment(Long userId, Long reviewId, String text) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		Review review = reviewRepository.findById(reviewId).
				orElseThrow(() -> new RuntimeException("Review not found"));
		
		ReviewComment comment = new ReviewComment();
        comment.setUser(user);
        comment.setReview(review);
        comment.setCommentText(text);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
	}

	@Override
	public List<ReviewComment> getComments(Long reviewId) {
		return commentRepository.findByReviewIdOrderByCreatedAtAsc(reviewId);
	}
	
	
	
}

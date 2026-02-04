package com.moctale.repository;

import java.util.List;
import com.moctale.models.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    List<ReviewComment> findByReviewIdOrderByCreatedAtAsc(Long reviewId);
}


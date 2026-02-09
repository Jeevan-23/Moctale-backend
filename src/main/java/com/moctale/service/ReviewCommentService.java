package com.moctale.service;

import java.util.List;

import com.moctale.models.ReviewComment;

public interface ReviewCommentService {

    ReviewComment addComment(Long userId, Long reviewId, String text);

    List<ReviewComment> getComments(Long reviewId);
}

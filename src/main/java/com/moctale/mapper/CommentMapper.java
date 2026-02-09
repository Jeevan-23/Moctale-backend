package com.moctale.mapper;

import com.moctale.dto.CommentResponse;
import com.moctale.models.ReviewComment;

public final class CommentMapper {

    private CommentMapper() {}

    public static CommentResponse toResponse(ReviewComment comment) {
        CommentResponse dto = new CommentResponse();
        dto.setId(comment.getId());
        dto.setUsername(comment.getUser().getUsername());
        dto.setCommentText(comment.getCommentText());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}

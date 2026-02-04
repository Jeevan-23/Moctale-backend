package com.moctale.dto;

import java.time.LocalDateTime;

import com.moctale.models.ReviewType;

public class ReviewResponse {

    private Long id;
    private String username;
    private ReviewType reviewType;
    private String commentText;
    private long likeCount;
    private LocalDateTime createdAt;
}


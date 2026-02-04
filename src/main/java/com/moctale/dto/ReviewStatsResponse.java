package com.moctale.dto;

import java.util.Map;

import com.moctale.models.ReviewType;

public class ReviewStatsResponse {

    private Map<ReviewType, Long> counts;
    private long totalReviews;
}

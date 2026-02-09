package com.moctale.mapper;

import java.util.Map;

import com.moctale.dto.ReviewStatsResponse;
import com.moctale.models.ReviewType;

public final class ReviewStatsMapper {

    private ReviewStatsMapper() {}

    public static ReviewStatsResponse toResponse(
            Map<ReviewType, Long> counts) {

        ReviewStatsResponse dto = new ReviewStatsResponse();
        dto.setCounts(counts);
        dto.setTotalReviews(
                counts.values().stream().mapToLong(Long::longValue).sum()
        );
        return dto;
    }
}

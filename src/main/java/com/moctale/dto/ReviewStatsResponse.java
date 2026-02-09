package com.moctale.dto;

import java.util.Map;

import com.moctale.models.ReviewType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewStatsResponse {

    private Map<ReviewType, Long> counts;
    private long totalReviews;
}

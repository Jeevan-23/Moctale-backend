package com.moctale.service;

import java.util.List;

import com.moctale.dto.ContentResponse;
import com.moctale.dto.ReviewResponse;

public interface LeaderboardService {

    List<ContentResponse> mostReviewed(int limit);

    List<ContentResponse> trending(int limit);

    List<ReviewResponse> mostLikedReviews(int limit);
}


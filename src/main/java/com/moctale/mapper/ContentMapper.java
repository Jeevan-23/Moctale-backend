package com.moctale.mapper;

import java.util.List;

import com.moctale.dto.ContentDetailResponse;
import com.moctale.dto.ContentResponse;
import com.moctale.dto.ReviewStatsResponse;
import com.moctale.models.Content;

public final class ContentMapper {

    private ContentMapper() {}

    public static ContentResponse toResponse(Content content) {
        ContentResponse dto = new ContentResponse();
        dto.setId(content.getId());
        dto.setTitle(content.getTitle());
        dto.setContentType(content.getContentType());
        dto.setReleaseDate(content.getReleaseDate());
        return dto;
    }

    public static ContentDetailResponse toDetailResponse(
            Content content,
            List<String> genres,
            List<String> platforms,
            ReviewStatsResponse reviewStats) {

        ContentDetailResponse dto = new ContentDetailResponse();
        dto.setId(content.getId());
        dto.setTitle(content.getTitle());
        dto.setContentType(content.getContentType());
        dto.setDescription(content.getDescription());
        dto.setReleaseDate(content.getReleaseDate());
        dto.setStatus(content.getStatus());
        dto.setGenres(genres);
        dto.setPlatforms(platforms);
        dto.setReviewStats(reviewStats);
        return dto;
    }
}


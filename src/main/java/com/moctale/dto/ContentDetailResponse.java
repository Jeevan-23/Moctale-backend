package com.moctale.dto;

import java.time.LocalDate;
import java.util.List;

import com.moctale.models.ContentStatus;
import com.moctale.models.ContentType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentDetailResponse {

    private Long id;
    private String title;
    private ContentType contentType;
    private String description;
    private LocalDate releaseDate;
    private ContentStatus status;

    private List<String> genres;
    private List<String> platforms;

    private ReviewStatsResponse reviewStats;
}


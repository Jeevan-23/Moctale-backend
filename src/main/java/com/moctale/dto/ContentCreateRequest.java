package com.moctale.dto;

import java.time.LocalDate;
import java.util.List;

import com.moctale.models.ContentStatus;
import com.moctale.models.ContentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ContentCreateRequest {

    @NotBlank
    private String title;

    @NotNull
    private ContentType contentType;

    private String description;

    private LocalDate releaseDate;

    private Integer durationMinutes;

    private Integer seasonsCount;

    private ContentStatus status;

    @NotEmpty
    private List<String> genres;

    @NotEmpty
    private List<String> platforms;
}


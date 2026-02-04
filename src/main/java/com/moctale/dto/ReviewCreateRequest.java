package com.moctale.dto;

import com.moctale.models.ReviewType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewCreateRequest {

    @NotNull
    private ReviewType reviewType;

    @Size(max = 1000)
    private String comment;
}


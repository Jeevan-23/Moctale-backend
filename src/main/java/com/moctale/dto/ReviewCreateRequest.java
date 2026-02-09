package com.moctale.dto;

import com.moctale.models.ReviewType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ReviewCreateRequest {

    @NotNull
    private ReviewType reviewType;

    @Size(max = 1000)
    private String comment;
}


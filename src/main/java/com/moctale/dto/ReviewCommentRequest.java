package com.moctale.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReviewCommentRequest {

    @NotBlank
    @Size(max = 500)
    private String commentText;
}

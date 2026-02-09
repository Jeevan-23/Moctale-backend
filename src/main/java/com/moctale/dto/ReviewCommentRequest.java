package com.moctale.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ReviewCommentRequest {

    @NotBlank
    @Size(max = 500)
    private String commentText;
}

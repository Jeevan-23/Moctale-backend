package com.moctale.dto;

import jakarta.validation.constraints.Size;

public class ProfileUpdateRequest {

    @Size(max = 500)
    private String bio;
}


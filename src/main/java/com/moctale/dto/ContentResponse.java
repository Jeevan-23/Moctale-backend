package com.moctale.dto;

import java.time.LocalDate;

import com.moctale.models.ContentType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentResponse {

    private Long id;
    private String title;
    private ContentType contentType;
    private LocalDate releaseDate;
}

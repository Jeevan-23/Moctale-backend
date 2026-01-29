package com.moctale.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContentGenreId implements Serializable {
    private Long contentId;
    private Long genreId;
}

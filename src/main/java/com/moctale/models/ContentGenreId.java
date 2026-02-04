package com.moctale.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ContentGenreId implements Serializable {

    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "genre_id")
    private Long genreId;

    // equals() and hashCode() are MANDATORY
}

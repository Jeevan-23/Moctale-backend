package com.moctale.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
@Table(name = "content_genres")
public class ContentGenre {

    @EmbeddedId
    private ContentGenreId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("contentId")   
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("genreId")     
    @JoinColumn(name = "genre_id")
    private Genre genre;
}

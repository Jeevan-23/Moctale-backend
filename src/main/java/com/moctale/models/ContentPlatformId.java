package com.moctale.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContentPlatformId implements Serializable {
    private Long contentId;
    private Long platformId;
}

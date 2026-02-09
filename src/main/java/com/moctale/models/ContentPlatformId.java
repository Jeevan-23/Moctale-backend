package com.moctale.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;

@Embeddable
@AllArgsConstructor
public class ContentPlatformId implements Serializable {
	private Long contentId;
    private Long platformId;
}

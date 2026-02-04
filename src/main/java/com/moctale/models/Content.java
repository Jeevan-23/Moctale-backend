package com.moctale.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "content")
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "content_type", nullable = false)
	private ContentType contentType;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	private LocalDate releaseDate;
	
//	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "poster_image")
	private byte[] posterImage;
	
	private Integer durationMinutes;
	
	private Integer seasonsCount;
	
	@Enumerated(EnumType.STRING)
	private ContentStatus status;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
}
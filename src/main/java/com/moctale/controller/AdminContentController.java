package com.moctale.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moctale.dto.ContentCreateRequest;
import com.moctale.dto.ContentResponse;
import com.moctale.mapper.ContentMapper;
import com.moctale.models.Content;
import com.moctale.models.ContentGenre;
import com.moctale.models.ContentGenreId;
import com.moctale.models.ContentPlatform;
import com.moctale.models.ContentPlatformId;
import com.moctale.models.Genre;
import com.moctale.models.Platform;
import com.moctale.repository.ContentGenreRepository;
import com.moctale.repository.ContentPlatformRepository;
import com.moctale.service.ContentService;
import com.moctale.service.GenreService;
import com.moctale.service.PlatformService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/contetnt")
public class AdminContentController {
	
	private final ContentService contentService;
    private final GenreService genreService;
    private final PlatformService platformService;
    private final ContentGenreRepository contentGenreRepository;
    private final ContentPlatformRepository contentPlatformRepository;

    public AdminContentController(
            ContentService contentService,
            GenreService genreService,
            PlatformService platformService,
            ContentGenreRepository contentGenreRepository,
            ContentPlatformRepository contentPlatformRepository) {
        this.contentService = contentService;
        this.genreService = genreService;
        this.platformService = platformService;
        this.contentGenreRepository = contentGenreRepository;
        this.contentPlatformRepository = contentPlatformRepository;
    }
    
    @PostMapping
    public ContentResponse createContent(
    		@RequestBody @Valid ContentCreateRequest request) {
    	Content content = new Content();
        content.setTitle(request.getTitle());
        content.setContentType(request.getContentType());
        content.setDescription(request.getDescription());
        content.setReleaseDate(request.getReleaseDate());
        content.setDurationMinutes(request.getDurationMinutes());
        content.setSeasonsCount(request.getSeasonsCount());
        content.setStatus(request.getStatus());

        Content saved = contentService.addContent(content);

        // genres
        request.getGenres().forEach(g -> {
            Genre genre = genreService.getOrCreate(g);
            contentGenreRepository.save(
                    new ContentGenre(
                            new ContentGenreId(saved.getId(), genre.getId()),
                            saved,
                            genre
                    )
            );
        });

        // platforms
        request.getPlatforms().forEach(p -> {
            Platform platform = platformService.getOrCreate(p);
            contentPlatformRepository.save(
                    new ContentPlatform(
                            new ContentPlatformId(saved.getId(), platform.getId()),
                            saved,
                            platform
                    )
            );
        });

        return ContentMapper.toResponse(saved);
    }
	
	
}

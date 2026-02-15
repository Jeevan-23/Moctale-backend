package com.moctale.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moctale.dto.ContentDetailResponse;
import com.moctale.dto.ContentResponse;
import com.moctale.dto.ReviewStatsResponse;
import com.moctale.mapper.ContentMapper;
import com.moctale.mapper.ReviewStatsMapper;
import com.moctale.models.Content;
import com.moctale.models.ContentGenre;
import com.moctale.models.ContentPlatform;
import com.moctale.models.ContentType;
import com.moctale.models.ReviewType;
import com.moctale.repository.ContentGenreRepository;
import com.moctale.repository.ContentPlatformRepository;
import com.moctale.service.ContentService;
import com.moctale.service.ReviewService;

@RestController
@RequestMapping("api/content")
public class ContentController {
	private final ContentService contentService;
    private final ReviewService reviewService;
    private final ContentGenreRepository contentGenreRepository;
    private final ContentPlatformRepository contentPlatformRepository;
    
    
    public ContentController(
            ContentService contentService,
            ReviewService reviewService,
            ContentGenreRepository contentGenreRepository,
            ContentPlatformRepository contentPlatformRepository) {
        this.contentService = contentService;
        this.reviewService = reviewService;
        this.contentGenreRepository = contentGenreRepository;
        this.contentPlatformRepository = contentPlatformRepository;
    }
    
    
    @GetMapping
    public List<ContentResponse> search(@RequestParam(required = false) String q) {
        List<Content> contentList =
                (q == null || q.isBlank())
                        ? contentService.getByType(null)
                        : contentService.searchByTitle(q);

        return contentList.stream()
                .map(ContentMapper::toResponse)
                .toList();
    }

    
    @GetMapping("/{id}")
    public ContentDetailResponse getContent(@PathVariable Long id) {
    	Content content = contentService.getContentById(id);
    	
    	List<String> genres = contentGenreRepository.findByContentId(id)
    			.stream().map((ContentGenre cg) -> cg.getGenre().getName())
    			.toList();
    	
    	List<String> platforms = contentPlatformRepository.findByContentId(id)
                .stream()
                .map((ContentPlatform cp) -> cp.getPlatform().getName())
                .toList();
    	
    	Map<ReviewType, Long> counts =
                reviewService.getReviewDistribution(id);

        ReviewStatsResponse stats =
                ReviewStatsMapper.toResponse(counts);

        return ContentMapper.toDetailResponse(
                content, genres, platforms, stats
        );
    }
    
    @GetMapping("/search")
    public Page<ContentResponse> searchAdvanced(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) ContentType type,
            @RequestParam(required = false) String genre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return contentService.searchAdvanced(
                title,
                type,
                genre,
                page,
                size
        );
    }

}

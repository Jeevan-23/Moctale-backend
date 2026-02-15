package com.moctale.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.moctale.dto.ContentResponse;
import com.moctale.models.Content;
import com.moctale.models.ContentType;

public interface ContentService {
	Content addContent(Content content);
	
	Content getContentById(Long Id);
	
	List<Content> searchByTitle(String title);
	
	List<Content> getByType(ContentType type);
	
	Page<ContentResponse> searchAdvanced(
	        String title,
	        ContentType type,
	        String genre,
	        int page,
	        int size
	);

}

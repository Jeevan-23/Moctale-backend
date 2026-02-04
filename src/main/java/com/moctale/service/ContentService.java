package com.moctale.service;

import java.util.List;

import com.moctale.models.Content;
import com.moctale.models.ContentType;

public interface ContentService {
	Content addContent(Content content);
	
	Content getContentById(Long Id);
	
	List<Content> searchByTitle(String title);
	
	List<Content> getByType(ContentType type);
}

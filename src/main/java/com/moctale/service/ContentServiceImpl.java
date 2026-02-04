package com.moctale.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moctale.models.Content;
import com.moctale.models.ContentType;
import com.moctale.repository.ContentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {

	private final ContentRepository contentRepository;
	
	public ContentServiceImpl(ContentRepository crt) {
		this.contentRepository = crt;
	}
	
	@Override
	public Content addContent(Content content) {
		return contentRepository.save(content);
	}

	@Override
	public Content getContentById(Long Id) {
		return contentRepository.findById(Id)
				.orElseThrow(() -> new RuntimeException("Content not found"));
	}

	@Override
	public List<Content> searchByTitle(String title) {
		return contentRepository.findByTitleContainingIgnoreCase(title);
	}

	@Override
	public List<Content> getByType(ContentType type) {
		return contentRepository.findByContentType(type);
	}

}

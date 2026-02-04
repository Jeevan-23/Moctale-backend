package com.moctale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moctale.models.Content;
import com.moctale.models.ContentType;


public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByContentType(ContentType contentType);

    List<Content> findByTitleContainingIgnoreCase(String title);
}

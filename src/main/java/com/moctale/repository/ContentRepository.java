package com.moctale.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moctale.models.Content;
import com.moctale.models.ContentType;


public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByContentType(ContentType contentType);

    List<Content> findByTitleContainingIgnoreCase(String title);

    @Query("""
    	    SELECT DISTINCT c
    	    FROM Content c
    	    LEFT JOIN ContentGenre cg ON cg.content.id = c.id
    	    LEFT JOIN Genre g ON cg.genre.id = g.id
    	    WHERE (:title IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%')))
    	    AND (:type IS NULL OR c.contentType = :type)
    	    AND (:genre IS NULL OR LOWER(g.name) = LOWER(:genre))
    	""")
    	Page<Content> searchAdvanced(
    	        @Param("title") String title,
    	        @Param("type") ContentType type,
    	        @Param("genre") String genre,
    	        Pageable pageable
    	);

}

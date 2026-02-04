package com.moctale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.moctale.models.ContentGenre;
import com.moctale.models.ContentGenreId;

public interface ContentGenreRepository
extends JpaRepository<ContentGenre, ContentGenreId> {

List<ContentGenre> findByGenreId(Long genreId);

List<ContentGenre> findByContentId(Long contentId);
}

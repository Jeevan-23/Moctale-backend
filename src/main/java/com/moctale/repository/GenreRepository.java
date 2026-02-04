package com.moctale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moctale.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
	Optional<Genre> findByName(String name);
}

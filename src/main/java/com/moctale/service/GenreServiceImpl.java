package com.moctale.service;

import com.moctale.models.Genre;
import com.moctale.repository.GenreRepository;

public class GenreServiceImpl implements GenreService{

	private final GenreRepository genreRepository;
	
	public GenreServiceImpl(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}
	@Override
	public Genre getOrCreate(String name) {
		return genreRepository.findByName(name)
				.orElseGet(() -> {
					Genre genre = new Genre();
					genre.setName(name);
					return genreRepository.save(genre);
				});
	}

}

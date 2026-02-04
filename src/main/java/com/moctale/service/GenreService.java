package com.moctale.service;

import com.moctale.models.Genre;

public interface GenreService {
	Genre getOrCreate(String name);
}

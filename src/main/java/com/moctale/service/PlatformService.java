package com.moctale.service;

import com.moctale.models.Platform;

public interface PlatformService {
	Platform getOrCreate(String name);
}

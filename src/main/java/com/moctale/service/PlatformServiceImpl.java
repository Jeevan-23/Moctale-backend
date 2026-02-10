package com.moctale.service;

import org.springframework.stereotype.Service;

import com.moctale.models.Platform;
import com.moctale.repository.PlatformRepository;


@Service
public class PlatformServiceImpl implements PlatformService{
	private final PlatformRepository platformRepository;
	
	public PlatformServiceImpl(PlatformRepository pf) {
		this.platformRepository = pf;
	}
	
	@Override
	public Platform getOrCreate(String name) {
		return platformRepository.findByName(name)
				.orElseGet(() -> {
					Platform platform = new Platform();
					platform.setName(name);
					return platformRepository.save(platform);
				});
	}
}

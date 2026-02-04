package com.moctale.repository;

import java.util.Optional;
import com.moctale.models.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long>{
	Optional<Platform> findByName(String name);
}

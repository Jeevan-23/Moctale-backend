package com.moctale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.moctale.models.ContentPlatform;
import com.moctale.models.ContentPlatformId;

public interface ContentPlatformRepository
extends JpaRepository<ContentPlatform, ContentPlatformId> {

List<ContentPlatform> findByContentId(Long contentId);
}

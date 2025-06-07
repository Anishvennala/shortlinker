package com.shortlinker.repository;

import com.shortlinker.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Integer> {
    Optional<UrlMapping> findByShortCode(String shortCode);
    Optional<UrlMapping> findByOriginalUrl(String originalUrl);
}

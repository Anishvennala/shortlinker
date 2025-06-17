//Repository communicates to db by extending the model/entity class.
//This queries, saves and updates data as the JPA writing sql in the backend for client

package com.shortlinker.repository;

import com.shortlinker.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Integer> {
    Optional<UrlMapping> findByShortCode(String shortCode);

    //Spring sees findByOriginalUrl and automatically creates the SQL behind the scenes:
    //SELECT * FROM url_mapping WHERE original_url = ?
    Optional<UrlMapping> findByOriginalUrl(String originalUrl);
}

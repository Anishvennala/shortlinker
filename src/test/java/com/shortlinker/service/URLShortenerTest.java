package com.shortlinker.service;

import com.shortlinker.model.UrlMapping;
import com.shortlinker.repository.UrlMappingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class URLShortenerTest {

    private UrlMappingRepository repository;
    private URLShortener shortener;

    @BeforeEach
    void setUp() {
        repository = mock(UrlMappingRepository.class);
        shortener = new URLShortener();
        shortener.repository = repository; // manual injection
    }

    @Test
    void testShortenNewUrl() {
        String longUrl = "https://example.com";

        String code = shortener.shortenURL(longUrl);

        assertNotNull(code);
        verify(repository).save(any(UrlMapping.class));
    }


}

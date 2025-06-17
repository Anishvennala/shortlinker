package com.shortlinker.controller;

import com.shortlinker.service.URLShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


import java.net.URI;
//./mvnw spring-boot:run in command terminal to run

/**
 * When a user submits a long URL to be shortened,
 * the POST /shorten endpoint accepts the request and forwards it to the service layer.
 * The service checks if the URL already exists using the UrlMappingRepository, and if not,
 * it generates a short code, stores a new UrlMapping model instance in the database, and
 * returns the short code to the client.
 * <p>
 * Later, when a user accesses the short URL (via GET /{shortCode}),
 * the controller uses the short code to query the database through the service and repository.
 * If a match is found, the original URL is returned via an HTTP 302 redirect;
 * otherwise, a 404 response is returned.
 */
@RestController
public class URLController {

    @Autowired
    private URLShortener service;



    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
        String shortCode = service.shortenURL(originalUrl);
        return ResponseEntity.ok(shortCode);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode) {
        return service.getOriginalUrl(shortCode)
                .map(originalUrl -> ResponseEntity
                        .status(HttpStatus.FOUND) // 302 Redirect
                        .location(URI.create(originalUrl))
                        .build())
                .orElse(ResponseEntity.notFound().build());
    }


}
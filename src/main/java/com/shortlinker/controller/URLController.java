package com.shortlinker.controller;

import com.shortlinker.service.URLShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


import java.net.URI;
//./mvnw spring-boot:run in command terminal to run

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
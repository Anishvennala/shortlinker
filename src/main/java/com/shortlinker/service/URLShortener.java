package com.shortlinker.service;

import com.shortlinker.util.HashUtil;
import java.util.HashMap;

import com.shortlinker.model.UrlMapping;
import com.shortlinker.repository.UrlMappingRepository;
import com.shortlinker.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

import java.util.LinkedList;
import java.util.Queue;


@Service
public class URLShortener {

    //represents repo connected to sql DB
    @Autowired
    UrlMappingRepository repository;

    //In-memory cache: shortCode -> originalUrl
    private final HashMap<String, String> cache = new HashMap<>();
    private final static int CACHE_SIZE = 5;
    private Queue<String> order = new LinkedList<>();
    public String shortenURL(String input) {
        String longUrl = input.replaceAll("\\s", "");

        // Check if there is already an existing entity in the table
        Optional<UrlMapping> existingEntity = repository.findByOriginalUrl(longUrl);
        String shortCode;
        if (existingEntity.isPresent()) {
            shortCode = existingEntity.get().getShortCode();
        }
        else{
            //use md5 hash to create a unique short code
            shortCode = HashUtil.getMD5Hash(longUrl).substring(0,7);

            //handle collisions
            int incr = 0;
            String newUrl;
            while(repository.findByShortCode(shortCode).isPresent()){
                newUrl = longUrl + incr;
                shortCode = HashUtil.getMD5Hash(newUrl).substring(0,7);
                incr++;
            }

            //save a new entity to the table in DB
            UrlMapping newMapping = new UrlMapping(longUrl, shortCode);
            repository.save(newMapping);
        }

        if(!cache.containsKey(shortCode)) {
            //add to and adjust the cache
            cache.put(shortCode, longUrl);
            adjustCache(shortCode);
        }
        return shortCode;
    }

    public Optional<String> getOriginalUrl(String shortCode) {
        //check in cache before repo DB
        if(cache.containsKey(shortCode)){
            System.out.println("From cache");
            return Optional.of(cache.get(shortCode));
        }

        //now check in DB (not in cache)
        Optional<UrlMapping> urlMapping = repository.findByShortCode(shortCode);
        return urlMapping.map(UrlMapping::getOriginalUrl);
    }

    private void adjustCache(String shortCode){
        if (!order.contains(shortCode)) {
            order.offer(shortCode);
        }
        if (order.size() > CACHE_SIZE) {
            String removed = order.poll();
            if (removed != null) {
                cache.remove(removed);
                System.out.println(">> Cache evicted: " + removed);
            }
        }
        // Debugging output
        System.out.println(">> Current Cache:");
        for (String code : order) {
            System.out.println(code + " -> " + cache.get(code));
        }
    }



}

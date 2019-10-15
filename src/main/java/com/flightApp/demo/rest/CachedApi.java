package com.flightApp.demo.rest;

import com.flightApp.demo.cache.SimpleCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public abstract  class CachedApi {

    /**
     * expires in : 10 seconds
     * max size: 1000
     */
    private SimpleCache<String, ResponseEntity> cache = new SimpleCache<>(10_000, 1000);
}

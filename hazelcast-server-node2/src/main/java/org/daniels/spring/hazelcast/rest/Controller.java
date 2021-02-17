package org.daniels.spring.hazelcast.rest;


import org.daniels.spring.hazelcast.CacheInstance;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cars")
public class Controller {

    private final CacheInstance cacheClient;

    public Controller(CacheInstance cacheClient) {
        this.cacheClient = cacheClient;
    }

    @PostMapping(path = "/{key}/{carName}", produces= MediaType.APPLICATION_JSON_VALUE)
    public String put(@PathVariable String key, @PathVariable String carName) {
        return cacheClient.put(key, carName);
    }

    @GetMapping(value = "/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable String key) {
        return cacheClient.get(key);
    }
}

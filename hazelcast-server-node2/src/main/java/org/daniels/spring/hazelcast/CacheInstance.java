package org.daniels.spring.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheInstance {

    public static final String CARS = "cars";
    private final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(createConfig());

    public String put(String key, String value){
        hazelcastInstance.getMap(CARS).putIfAbsent(key, value);
        log.info("Caches put - instance: {}, key: {}, value: {}", hazelcastInstance, key, value);
        return value;

    }

    public String get(String key){
        Object value = hazelcastInstance.getMap(CARS).get(key);
        log.info("Caches get - instance: {}, key: {}, value: {}", hazelcastInstance, key, value);
        return value != null ? value.toString() : "unknown";
    }

    public Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
        return config;
    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(CARS);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(360);
        return mapConfig;
    }
}

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
    private final HazelcastInstance hazelcastInstance;

    public CacheInstance() {
        hazelcastInstance = Hazelcast.newHazelcastInstance(createConfig());;
    }

    public String put(String key, String carName){
        /*
        IMap<String, String> map = hazelCast.getMap(CARS);
        String value = map.putIfAbsent(key, carName);
        log.info("Caches put - client: {}, number: {}, car: {}, value: {}",
                hazelCast, key, carName, value);
        return value;
        */
        return carName;
    }

    public String get(String key){
        /*
        IMap<String, String> map = hazelCast.getMap(CARS);
        String value = map.get(key);
        log.info("Cache get - client: {}, key: {},  value: {}", hazelCast, key, value);
        return value;
        */
        return key;
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
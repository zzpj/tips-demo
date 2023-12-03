package com.example.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleInMemoryCache {

    private static final Map<String, Object> CACHE = new ConcurrentHashMap<>();

    public static synchronized Object put(String key, Object value) {
        return CACHE.put(key, value);
    }

    public static synchronized Object get(String key) {
        return CACHE.get(key);
    }

    public static synchronized boolean containsKey(String key) {
        return CACHE.containsKey(key);
    }

    public static void clear() {
        CACHE.clear();
    }
}




package com.example.cache;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TimeBasedInMemoryCache {

    private static final Map<String, CustomerNamesCache> INSTANCE = new ConcurrentHashMap<>();

    public static synchronized boolean isCached(final String key) {
        CustomerNamesCache timedCachedObject = INSTANCE.get(key);
        if (Objects.nonNull(timedCachedObject)) {
            return timedCachedObject.lastRefreshedTimestamp.plus(10, ChronoUnit.MINUTES).isAfter(Instant.now());
        }
        return false;
    }

    public static synchronized void put(final String key, final Set<String> customerNames) {
        CustomerNamesCache toBeCached = CustomerNamesCache.builder()
                .customers(customerNames)
                .lastRefreshedTimestamp(Instant.now())
                .build();
        INSTANCE.put(key, toBeCached);
    }

    public static synchronized Set<String> get(final String key) {
        CustomerNamesCache timedCachedObject = INSTANCE.get(key);
        if (Objects.nonNull(timedCachedObject)) {
            return INSTANCE.get(key).getCustomers();
        }
        return null;
    }

    public static synchronized boolean containsKey(final String key) {
        return INSTANCE.containsKey(key);
    }

    static synchronized void clear() {
        INSTANCE.clear();
    }

    static synchronized void invalidate(final String key) {
        if (containsKey(key)) {
            INSTANCE.get(key).setLastRefreshedTimestamp(Instant.EPOCH);
        }
    }

    @Data
    @Builder
    private static class CustomerNamesCache {
        private Instant lastRefreshedTimestamp;
        private Set<String> customers;
    }
}

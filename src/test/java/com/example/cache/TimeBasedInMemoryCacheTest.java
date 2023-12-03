package com.example.cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TimeBasedInMemoryCacheTest {
    private static final String KEY = "my-key";

    @Test
    void cacheShouldBeValid() {
        TimeBasedInMemoryCache.put(KEY, Set.of());
        boolean validFlag = TimeBasedInMemoryCache.isCached(KEY);
        assertTrue(validFlag);
    }

    @Test
    void noCacheForNonExistingKey() {
        assertFalse(TimeBasedInMemoryCache.isCached("any-non-existing-key"));
    }

    @Test
    void cacheShouldNotBeValidDueToLastRefreshTimestampReset() {
        TimeBasedInMemoryCache.put(KEY, Set.of());
        TimeBasedInMemoryCache.invalidate(KEY);
        assertFalse(TimeBasedInMemoryCache.isCached(KEY));
    }

    @Test
    void getValidObjectFromCache() {
        Set<String> expected = Set.of("A1", "A3", "A2");
        TimeBasedInMemoryCache.put(KEY, expected);
        assertEquals(expected, TimeBasedInMemoryCache.get(KEY));
    }

    @Test
    void getNullFromNonExistingKey() {
        assertNull(TimeBasedInMemoryCache.get(KEY));
    }

    @AfterEach
    void afterEach() {
        TimeBasedInMemoryCache.clear();
    }
}
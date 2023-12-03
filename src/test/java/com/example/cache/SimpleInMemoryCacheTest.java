package com.example.cache;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleInMemoryCacheTest {
    @Test
    void demoHowSimpleCacheWorks() {

        String key = "my-key";
        MyDemoClass myDemoClass = new MyDemoClass(1L, "MyName");

        SimpleInMemoryCache.put(key, myDemoClass);
        assertTrue(SimpleInMemoryCache.containsKey(key));

        Object actual = SimpleInMemoryCache.get(key);
        assertEquals(myDemoClass, actual);

        SimpleInMemoryCache.clear();
        assertFalse(SimpleInMemoryCache.containsKey(key));
    }


    @AllArgsConstructor
    static class MyDemoClass {

        private Long id;
        private String name;
    }
}
package com.example.jdk.update.jdk19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreallocatedCollections {

    // to create an ArrayList for a known number of elements (e.g., 120), we can do it as follows since ever:
    List<String> list = new ArrayList<>(120);

    // we have always been able to generate a HashMap as follows:
    Map<String, Integer> map = new HashMap<>(120);

    /*
    HashMap is initialized with a default load factor of 0.75. This means that as soon as the HashMap is 75% full,
    it is rebuilt ("rehashed") with double the size. This ensures that the elements are distributed as evenly as possible
    across the HashMap's buckets and that as few buckets as possible contain more than one element.
    Thus, the HashMap initialized with a capacity of 120 can only hold 120 × 0.75 = 90 mappings.
    In Java 19 makes it easier:
     */
    Map<String, Integer> mapFromJDk19 = HashMap.newHashMap(120);
}

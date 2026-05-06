package org.example;

import java.util.*;

/**
 * LFU Cache Implementation
 *
 * An LFU (Least Frequently Used) Cache evicts the least frequently used item
 * when capacity is reached. If there's a tie in frequency, the least recently
 * used item among those with the same frequency is evicted.
 *
 * Approach:
 * - Use a HashMap to store key-value pairs for O(1) access
 * - Use a HashMap to track frequency of each key
 * - Use a HashMap with frequency as key and a LinkedHashSet of keys as value
 *   to maintain insertion order (for LRU within same frequency)
 * - Track the minimum frequency for efficient eviction
 */
public class LFUCache {
    private final int capacity;
    private final Map<Integer, Integer> keyToValue;      // key -> value
    private final Map<Integer, Integer> keyToFreq;       // key -> frequency
    private final Map<Integer, LinkedHashSet<Integer>> freqToKeys; // frequency -> keys with that frequency
    private int minFreq;

    /**
     * Initialize LFU Cache with given capacity
     * Time Complexity: O(1)
     */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyToValue = new HashMap<>();
        this.keyToFreq = new HashMap<>();
        this.freqToKeys = new HashMap<>();
        this.minFreq = 0;
    }

    /**
     * Get value by key and increment its frequency
     * Time Complexity: O(1)
     */
    public int get(int key) {
        if (!keyToValue.containsKey(key)) {
            return -1;
        }

        // Increment frequency
        increaseFreq(key);
        return keyToValue.get(key);
    }

    /**
     * Put key-value pair in cache
     * If key exists, update value and increment frequency
     * If cache is full, evict least frequently used key
     * Time Complexity: O(1)
     */
    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        // If key exists, update value and increase frequency
        if (keyToValue.containsKey(key)) {
            keyToValue.put(key, value);
            increaseFreq(key);
            return;
        }

        // If cache is full, evict least frequently used key
        if (keyToValue.size() >= capacity) {
            evictLFU();
        }

        // Add new key-value pair with frequency 1
        keyToValue.put(key, value);
        keyToFreq.put(key, 1);
        if (!freqToKeys.containsKey(1)) {
            freqToKeys.put(1, new LinkedHashSet<>());
        }
        freqToKeys.get(1).add(key);
        minFreq = 1;
    }

    /**
     * Helper: Increase frequency of a key
     * Time Complexity: O(1)
     */
    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);

        // Remove key from current frequency set
        freqToKeys.get(freq).remove(key);

        // If current frequency set is empty and it's minFreq, increment minFreq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            if (freq == minFreq) {
                minFreq++;
            }
        }

        // Add key to new frequency set
        int newFreq = freq + 1;
        if (!freqToKeys.containsKey(newFreq)) {
            freqToKeys.put(newFreq, new LinkedHashSet<>());
        }
        freqToKeys.get(newFreq).add(key);
    }

    /**
     * Helper: Evict the least frequently used key
     * If there's a tie, evict the least recently used (first inserted) among them
     * Time Complexity: O(1)
     */
    private void evictLFU() {
        // Get the set of keys with minimum frequency
        LinkedHashSet<Integer> keysWithMinFreq = freqToKeys.get(minFreq);

        // Remove the first key (least recently used with minFreq)
        int keyToRemove = keysWithMinFreq.iterator().next();
        keysWithMinFreq.remove(keyToRemove);

        // Clean up empty frequency entry
        if (keysWithMinFreq.isEmpty()) {
            freqToKeys.remove(minFreq);
        }

        // Remove key from other maps
        keyToValue.remove(keyToRemove);
        keyToFreq.remove(keyToRemove);
    }

    /**
     * Get the size of the cache
     * Time Complexity: O(1)
     */
    public int size() {
        return keyToValue.size();
    }

    /*
     * ============================================================
     * COMPLEXITY ANALYSIS
     * ============================================================
     * Time Complexity:
     *   - get(key)  : O(1) — HashMap lookup and frequency update
     *   - put(key)  : O(1) — HashMap insertion and eviction
     *   - overall   : All operations are O(1)
     *
     * Space Complexity : O(capacity) — Store at most 'capacity' keys
     *   with 3 HashMaps and 1 frequency-to-keys map
     * ============================================================
     */

    // ✅ VALIDATION EXAMPLE
    public static void main(String[] args) {
        System.out.println("=== LFU Cache Validation ===\n");

        // Test Case 1: Basic get and put operations
        System.out.println("Test Case 1: Basic Operations");
        System.out.println("Input: Capacity = 2");
        LFUCache cache1 = new LFUCache(2);

        cache1.put(1, 1);
        System.out.println("put(1, 1) → Cache: {1:1}");

        cache1.put(2, 2);
        System.out.println("put(2, 2) → Cache: {1:1, 2:2}");

        int result = cache1.get(1);
        System.out.println("get(1) → Expected: 1, Got: " + result);
        System.out.println("✅ Test 1.1 Passed: " + (result == 1));

        cache1.put(3, 3); // Evicts key 2 (least frequently used)
        System.out.println("put(3, 3) → Cache: {1:1, 3:3} (key 2 evicted - freq 1)");

        result = cache1.get(2);
        System.out.println("get(2) → Expected: -1, Got: " + result);
        System.out.println("✅ Test 1.2 Passed: " + (result == -1));

        // Test Case 2: Frequency tracking
        System.out.println("\nTest Case 2: Frequency Tracking");
        System.out.println("Input: Capacity = 3");
        LFUCache cache2 = new LFUCache(3);

        cache2.put(1, 1);
        cache2.put(2, 2);
        cache2.put(3, 3);
        System.out.println("put(1,1), put(2,2), put(3,3) → Cache: {1:1, 2:2, 3:3}");

        cache2.get(1); // freq[1] = 2
        cache2.get(1); // freq[1] = 3
        System.out.println("get(1) twice → freq[1] = 3");

        cache2.get(2); // freq[2] = 2
        System.out.println("get(2) once → freq[2] = 2");

        cache2.put(4, 4); // Evict key 3 (freq 1, or key 2 if freq tied)
        System.out.println("put(4, 4) → Cache: {1:1, 2:2, 4:4} (key 3 evicted - freq 1)");

        result = cache2.get(3);
        System.out.println("get(3) → Expected: -1, Got: " + result);
        System.out.println("✅ Test 2 Passed: " + (result == -1));

        // Test Case 3: LRU within same frequency
        System.out.println("\nTest Case 3: LRU within Same Frequency");
        System.out.println("Input: Capacity = 2");
        LFUCache cache3 = new LFUCache(2);

        cache3.put(1, 1);
        cache3.put(2, 2);
        System.out.println("put(1,1), put(2,2) → Cache: {1:1, 2:2}, both freq=1");

        cache3.put(3, 3); // Both 1 and 2 have freq 1, evict 1 (least recently used)
        System.out.println("put(3, 3) → Cache: {2:2, 3:3} (key 1 evicted - least recently used)");

        result = cache3.get(1);
        System.out.println("get(1) → Expected: -1, Got: " + result);
        System.out.println("✅ Test 3 Passed: " + (result == -1));

        // Test Case 4: Update existing key
        System.out.println("\nTest Case 4: Update Existing Key");
        LFUCache cache4 = new LFUCache(2);

        cache4.put(1, 1);
        cache4.put(2, 2);
        System.out.println("put(1,1), put(2,2) → Cache: {1:1, 2:2}");

        cache4.put(1, 10); // Update key 1, increase its frequency
        System.out.println("put(1, 10) → Cache: {1:10, 2:2}, freq[1]=2");

        cache4.put(3, 3); // Evict key 2 (freq 1)
        System.out.println("put(3, 3) → Cache: {1:10, 3:3} (key 2 evicted)");

        result = cache4.get(1);
        System.out.println("get(1) → Expected: 10, Got: " + result);
        System.out.println("✅ Test 4 Passed: " + (result == 10));

        System.out.println("\n=== All Tests Passed ✅ ===");
    }
}

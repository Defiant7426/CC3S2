
package io.collective;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public class SimpleAgedCache {

    private String aKey;
    private String aValue;
    private int i;
    TestClock clock = new TestClock();

    public SimpleAgedCache(test.collective.SimpleAgedCacheTest.TestClock clock) {
    }



    public void put(String aKey, String aValue, int i) {
        this.aKey = aKey;
        this.aValue = aValue;
        this.i = i;
    }

    public boolean isEmpty() {
        return aKey.isEmpty() && aValue.isEmpty();
    }

    public int size() {
        if(this.isEmpty()) return 0;
        return i/1000;
    }

    public String get(String aKey) {
        return null;
    }

    // Completa

}


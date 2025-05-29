import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinearHashMapTest {

    private LinearHashMap<String, Integer> map;

    @BeforeEach
    public void setup() {
        map = new LinearHashMap<>(10);
    }

    @Test
    public void testPutAndGet() {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        assertEquals(1, map.get("a"));
        assertEquals(2, map.get("b"));
        assertEquals(3, map.get("c"));
    }

    @Test
    public void testUpdateValue() {
        map.put("x", 100);
        map.put("x", 999); // Update value for same key
        assertEquals(999, map.get("x"));
    }

    @Test
    public void testRemoveExistingKey() {
        map.put("test", 42);
        assertEquals(42, map.get("test"));

        map.remove("test");
        assertNull(map.get("test"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        map.put("a", 1);
        map.remove("nonexistent");
        assertEquals(1, map.get("a")); // Should still be there
    }

    @Test
    public void testCountCountsNonNullBuckets() {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        assertEquals(3, map.count());

        map.remove("b");
        assertEquals(2, map.count()); // only counts non-null buckets
    }

    @Test
    public void testCollisionsHandledWithChaining() {
        class KeyWithSameHash {
            private final String name;

            KeyWithSameHash(String name) {
                this.name = name;
            }

            @Override
            public int hashCode() {
                return 1; // force same hash for all keys
            }

            @Override
            public boolean equals(Object o) {
                return o instanceof KeyWithSameHash && ((KeyWithSameHash) o).name.equals(this.name);
            }

            @Override
            public String toString() {
                return name;
            }
        }

        LinearHashMap<KeyWithSameHash, Integer> collisionMap = new LinearHashMap<>(5);
        KeyWithSameHash k1 = new KeyWithSameHash("a");
        KeyWithSameHash k2 = new KeyWithSameHash("b");
        KeyWithSameHash k3 = new KeyWithSameHash("c");

        collisionMap.put(k1, 10);
        collisionMap.put(k2, 20);
        collisionMap.put(k3, 30);

        assertEquals(10, collisionMap.get(k1));
        assertEquals(20, collisionMap.get(k2));
        assertEquals(30, collisionMap.get(k3));
    }
    @Test
    public void testPutNullKey() {
        map.put(null, 100);
        assertEquals(100, map.get(null));
    }

    @Test
    public void testUpdateNullKey() {
        map.put(null, 1);
        map.put(null, 99); // обновление значения
        assertEquals(99, map.get(null));
    }

    @Test
    public void testRemoveNullKey() {
        map.put(null, 42);
        assertEquals(42, map.get(null));

        map.remove(null);
        assertNull(map.get(null));
    }

    @Test
    public void testPutNullValue() {
        map.put("key", null);
        assertNull(map.get("key"));
    }

    @Test
    public void testPutNullKeyAndValue() {
        map.put(null, null);
        assertNull(map.get(null));
    }

}


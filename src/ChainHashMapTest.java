import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChainHashMapTest {

    private ChainHashMap<String, Integer> map;

    @BeforeEach
    public void setUp() {
        map = new ChainHashMap<>(10);
    }

    @Test
    public void testPutAndGet() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertEquals(3, map.get("three"));
    }

    @Test
    public void testOverwriteValue() {
        map.put("key", 42);
        map.put("key", 100);
        // Существующий ключ перезаписывается
        assertEquals(100, map.get("key"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertNull(map.get("missing"));
    }

    @Test
    public void testRemove() {
        map.put("deleteMe", 99);
        assertEquals(99, map.get("deleteMe"));

        map.remove("deleteMe");
        assertNull(map.get("deleteMe"));
    }

    @Test
    public void testCount() {
        assertEquals(0, map.count());

        map.put("a", 1);
        map.put("b", 2);
        assertEquals(2, map.count());

        map.put("a", 3); // Существующий ключ перезаписывается
        assertEquals(2, map.count());
    }

    @Test
    public void testIsEmptyAndClear() {
        assertTrue(map.isEmpty());

        map.put("x", 10);
        assertFalse(map.isEmpty());

        map.clear();
        assertTrue(map.isEmpty());
        assertEquals(0, map.count());
    }

    @Test
    public void testNullKey() {
        map.put(null, 123);
        assertEquals(123, map.get(null));

        map.remove(null);
        assertNull(map.get(null));
    }
}


public class LinearHashMap<K, V> {
    private DoublyLinkedList<Entry<K, V>> table;
    public final int size;
    private final Entry<K, V> DELETED = new Entry<>(null, null);

    public LinearHashMap(int size) {
        this.size = size > 0 ? size : 16;
        table = new DoublyLinkedList<>();
        for (int i = 0; i < this.size; i++) {
            table.add(null);
        }
    }

    private int hash(K key) {
        return key == null ? 0 : hashFunc(key, size);
    }

    private static int hashFunc(Object key, int tableSize) {
        if (key == null) return 0;
        int h = key.hashCode();
        h ^= (h >>> 16);
        return (h & 0x7FFFFFFF) % tableSize;
    }

    public void put(K key, V value) {
        int index = hash(key);
        int originalIndex = index;

        do {
            Entry<K, V> entry = table.get(index);
            if (entry == null || entry == DELETED) {
                table.set(index, new Entry<>(key, value));
                return;
            } else if ((entry.key == null && key == null) || (entry.key != null && entry.key.equals(key))) {
                entry.value = value;
                return;
            }
            index = (index + 1) % size;
        } while (index != originalIndex);
    }

    public V get(K key) {
        int index = hash(key);
        int originalIndex = index;

        do {
            Entry<K, V> entry = table.get(index);
            if (entry == null) return null;
            if (entry != DELETED && ((key == null && entry.key == null) || (key != null && key.equals(entry.key)))) {
                return entry.value;
            }
            index = (index + 1) % size;
        } while (index != originalIndex);

        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        int originalIndex = index;

        do {
            Entry<K, V> entry = table.get(index);
            if (entry == null) return;
            if (entry != DELETED && ((key == null && entry.key == null) || (key != null && key.equals(entry.key)))) {
                table.set(index, DELETED);
                return;
            }
            index = (index + 1) % size;
        } while (index != originalIndex);
    }

    public int count() {
        int count = 0;
        for (Entry<K, V> entry : table) {
            if (entry != null && entry != DELETED) {
                count++;
            }
        }
        return count;
    }

    public void printTable() {
        for (int i = 0; i < table.size(); i++) {
            Entry<K, V> entry = table.get(i);
            if (entry != null && entry != DELETED) {
                System.out.println("Bucket " + i + ": Key = " + entry.key + ", Value = " + entry.value);
            }
        }
    }

    private static class Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
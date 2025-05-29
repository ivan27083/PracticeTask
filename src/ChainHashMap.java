public class ChainHashMap<T,V> {
    DoublyLinkedList<Entry<T, V>>[] table;
    public final int size;
    public ChainHashMap(int size){
        this.size = size > 0 ? size : 16;
        table = new DoublyLinkedList[this.size];
        for (int i = 0; i < table.length; i++) {
            table[i] = new DoublyLinkedList<>();
        }
    }

    private int hash(T key){
        return key == null ? 0 : hashFunc(key, size);
    }

    private static int hashFunc(Object key, int tableSize) {
        if (key == null) return 0;
        int h = key.hashCode();
        h ^= (h >>> 16);
        return (h & 0x7FFFFFFF) % tableSize;
    }

    public void put(T key, V value){
        int index = hash(key);
        Entry<T, V> newEntry = new Entry<>(key, value);
        for (Entry<T, V> entry : table[index]) {
            if (entry.key != null) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
            }
            else if (key == null) {
                entry.value = value;
            }
        }
        table[index].add(newEntry);
    }

    public V get(T key){
        int index = hash(key);
        for (Entry<T, V> entry : table[index]) {
            if (entry.key != null) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
            else if (key == null) {
                return entry.value;
            }

        }
        return null;
    }

    public void remove(T key){
        int index = hash(key);
        for (Entry<T, V> entry : table[index]) {
            if (entry.key != null) {
                if (entry.key.equals(key)) {
                    table[index].remove(entry);
                    return;
                }
            }
            else if (key == null) {
                table[index].remove(entry);
                return;
            }
        }
    }

    public int count(){
        if(table !=null){
            int size = 0;
            for (DoublyLinkedList<Entry<T, V>> list : table) {
                size += list.size();
            }
            return size;
        }else {
            return 0;
        }
    }

    public boolean isEmpty(){
        if(table !=null){
            for (DoublyLinkedList<Entry<T, V>> list : table) {
                if (!list.isEmpty()) {
                    return false;
                }
            }
            return true;
        }else {
            return true;
        }
    }

    public void clear(){
        if(table !=null){
            for (DoublyLinkedList<Entry<T, V>> entries : table) {
                entries.clear();
            }
        }
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.println("Bucket " + i + ":");
            for (Entry<T, V> entry : table[i]) {
                System.out.println("  Key: " + entry.key + ", Value: " + entry.value);
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
public class CustomHashMap <T,V> {
    V[] array;
    public CustomHashMap(int size){
        if(size>0) {
            array = (V[]) new Object[size];
        }
    }
    private int hash(T key){
        if(key!=null){
            return Math.abs(key.hashCode())%array.length;
        }else {
            return 0;
        }
    }
    public void put(T key, V value){
        if(key!=null) {
            int index = hash(key);
            array[index] = value;
        }
    }
    public V get(T key){
        if(key!=null) {
            int index = hash(key);
            return array[index];
        }else {
            return null;
        }
    }
    public void remove(T key){
        if(key!=null) {
            int index = hash(key);
            array[index] = null;
        }
    }
    public int size(){
        if(array!=null){
            return array.length;
        }else {
            return 0;
        }
    }
    public boolean isEmpty(){
        if(array!=null){
            return false;
        }else {
            return true;
        }
    }
    public void clear(){
        if(array!=null){
            array = (V[]) new Object[size()];
            for(int i=0;i<array.length;i++){
                array[i] = null;
            }
            array = null;
        }
    }
}

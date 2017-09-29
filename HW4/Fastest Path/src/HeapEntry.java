/**
 * Created by Anton Skudarnov on 21.03.2017.
 */
public class HeapEntry<K,V> {

    private K key;
    private V value;

    HeapEntry(K key, V value){
        this.key = key;
        this.value = value;
    }

    K getKey(){
        return key;
    }

    V getValue(){
        return value;
    }
}

/**
 * Created by Anton Skudarnov on 23.02.2017.
 */

/**
 * That class implement single entry for hash table
 * @param <K> key of the entry
 * @param <V> value of the entry
 */
public class HashEntry<K,V> {
    public K key;
    public V value;

    HashEntry(K key, V value){
        this.key = key;
        this.value =value;

    }

}

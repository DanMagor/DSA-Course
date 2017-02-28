/**
 * Created by Anton Skudarnov on 27.02.2017.
 */
public class TreeNote<K,V> {
    public K key;
    public V value;
    public TreeNote left;
    public TreeNote right;
    TreeNote(K key, V value){
        this.key = key;
        this.value = value;
        left = null;
        right = null;
    }
}

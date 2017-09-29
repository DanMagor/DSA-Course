/**
 * Created by Anton Skudarnov on 25.03.2017.
 */
public class Note<K extends Comparable<K>, V> {
   private K key;
    private V value;

    private Note<K, V> left;
    private Note<K, V> right;
    private Note<K, V> parent;
    private int height;


    Note(K key, V value) {
        this.key = key;
        this.value = value;
        left = null;
        right = null;
        height = 1;

    }

    public int getHeight() {
        return height;
    }



    public void setHeight(int height) {
        this.height = height;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    Note<K, V> getLeft() {
        return left;
    }

    public Note<K, V> getRight() {
        return right;
    }

    public Note<K, V> getParent() {
        return parent;
    }

    public V getValue() {
        return value;
    }

    public void setLeft(Note<K, V> left) {
        this.left = left;
    }

    public void setParent(Note<K, V> parent) {
        this.parent = parent;
    }

    public void setRight(Note<K, V> right) {
        this.right = right;
    }

    public void setValue(V value) {
        this.value = value;
    }
}